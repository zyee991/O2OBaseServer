package com.o2o.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.Dispatch;
import com.o2o.common.model.Order;
import com.o2o.common.model.ServiceOrder;
import com.o2o.common.model.User;
import com.o2o.service.ServiceOrderService;
import com.o2o.util.ApplicationProperties;
import com.o2o.util.HttpUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ServiceOrderController extends Controller {

	static ServiceOrderService serviceOrderService=new ServiceOrderService();
	
	public void index(){
		setAttr("title","服务订单管理");
		render("index.html");
	}
	
	public void tableData() {
		List<Record> list = serviceOrderService.tableData();
		System.out.println(list);
		renderJson(serviceOrderService.reload(list));
	}
	
	public void dispatch(){
		String id=getPara("id");
		String isforward=getPara("is_forward");
		String m_type="";
		List<Record>muserlist=null;
		//选择厂内人员
		if(isforward=="0"){
			m_type="A";
		 muserlist=serviceOrderService.finMuserList(m_type);
		}else{
			m_type="B";
			 muserlist=serviceOrderService.finMuserList(m_type);
		}
		Date date=new Date();
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String time=df2.format(date);
		setAttr("is_forward",isforward);
		setAttr("muserlist",muserlist);
		setAttr("service_order_id",id);
		setAttr("pac_id",UUID.randomUUID());
		setAttr("pac_stime",time);
		System.out.println(id+"----------------------"+isforward);
		render("dispatch.html");
	}
	//0----待审核   1---待评论    2----已完成
	//派单成功不改状态。。。由技师确认完成变为1
	public void saveDispatch(){
		Dispatch dispatch=getBean(Dispatch.class);
		String orderid=getPara("dispatch.serviceOrderId");
		System.out.println(orderid+"----------------orderid");
		serviceOrderService.save(dispatch);
		//开始获取模板
	   Map<String,String> resultMap=new HashMap<>();
		String mobanurl = ApplicationProperties.get("wxTemplateUrl");
		String wxSendUrl = ApplicationProperties.get("wxSendUrl");
		String mobanresult = HttpUtils.doGet(mobanurl);
		if (StringUtils.isNotBlank(mobanresult)) {
			JSONObject json = JSONObject.fromObject(mobanresult);
			JSONArray jsonarray = json.getJSONArray("template_list");
			String mobanid = "";
			String title = "订单状态通知";
			for (int i = 0; i < jsonarray.size(); i++) {
				JSONObject o1 = (JSONObject) jsonarray.get(i);
				if (o1.getString("title").equals(title)) {// jsonarray[i].get("title").equals("")
					mobanid = o1.getString("template_id");
					break;
				}
			}
			if(StringUtils.isNotBlank(mobanid)) {
				String orderId = getPara("id");
				ServiceOrder order = serviceOrderService.findOne(orderId);
				if(order != null) {
					String openId = order.getUserOpenid();
					User user = User.dao.findById(openId);
					if(user != null) {
						String type="serviceorder";
						String nickName = user.getUserNickname();
						String orderStatus = "已发货";
						String messageurl = wxSendUrl+"?user_openid="+openId+"&templete_id="+mobanid+"&nickname="+nickName+"&createtime="+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())+"&ordertatus="+orderStatus+"&type="+type;
						String messageresult = HttpUtils.doGet(messageurl);
						if(messageresult.contains("TemplateSenderResult")) {
							/*order.setServiceOrderStatus(1);
                            order.update();			*/
							resultMap.put("status", "1");
							resultMap.put("content","派单成功");
						} else {
							resultMap.put("status", "2");
							resultMap.put("content","消息发送失败");
						}
					} else {
						resultMap.put("status", "5");
						resultMap.put("content", "用户查询失败");
					}
				} else {
					resultMap.put("status", "4");
					resultMap.put("content", "订单信息不存在");
				}
			} else {
				resultMap.put("status", "3");
				resultMap.put("content", "模版获取失败");
			}
		} else {
			resultMap.put("status", "3");
			resultMap.put("content", "模版获取失败");
		}
		
		renderJson(resultMap);
		renderJavascript("window.location.href='/service_order'");
	}
	
////确认退款   状态改为3，订单状态改为2
	public void confirmSRefun(){
		String orderid=getPara("id");
		String paystatus=getPara("paystatus");
		String orderstatus=getPara("orderstatus");
		Map<String,String> resultMap = new HashMap<>();
		ServiceOrder order = serviceOrderService.findOne(orderid);
		if(order!=null){
			order.setServicePayStatus(Integer.parseInt(paystatus));
			order.setServiceOrderStatus(Integer.parseInt(orderstatus));
			order.update();
			resultMap.put("status", "1");
			resultMap.put("content","退款成功成功");
		}else {
			resultMap.put("status", "4");
			resultMap.put("content", "订单信息不存在");
		}
		renderJson(resultMap);
	}
	
	public void orderdetail(){
		String id=getPara("id");
		List<Record> list=serviceOrderService.getServiceOrderById(id);
		setAttr("list",list);
		render("order_detail.html");
	}
	
	public void paidandetail(){
		String id=getPara("id");
		List<Record>list=serviceOrderService.findPaidanDetail(id);
		setAttr("list",list);
		render("dispatch_detail.html");
	}
}
