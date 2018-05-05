package com.o2o.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.Order;
import com.o2o.common.model.User;
import com.o2o.service.OrderService;
import com.o2o.util.ApplicationProperties;
import com.o2o.util.HttpUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GoodsOrderController extends Controller {

	static OrderService orderService = new OrderService();

	public void index() {
		setAttr("title", "商品订单管理");
		render("index.html");
	}

	public void tableData() {
		List<Record> list = orderService.tableData();
		renderJson(orderService.reload(list));
	}

	public void confirm() {
		Map<String,String> resultMap = new HashMap<>();
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
				Order order = orderService.findOne(orderId);
				if(order != null) {
					String openId = order.getUserOpenid();
					User user = User.dao.findById(openId);
					if(user != null) {
						String type="goodsorder";
						String nickName = user.getUserNickname();
						String orderStatus = "已发货";
						String messageurl = wxSendUrl+"?user_openid="+openId+"&templete_id="+mobanid+"&nickname="+nickName+"&createtime="+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())+"&ordertatus="+orderStatus+"&type="+type;
						String messageresult = HttpUtils.doGet(messageurl);
						if(messageresult.contains("TemplateSenderResult")) {
							order.setOrderStatus(1);
							order.update();
			
							resultMap.put("status", "1");
							resultMap.put("content","发货成功");
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

	}

	public void orderdetail() {
		String id = getPara("id");
		List<Record> goodslist = orderService.findGoodsByOrderId(id);
		setAttr("goodslist", goodslist);
		render("order_detail.html");
	}
}
