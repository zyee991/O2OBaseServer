package com.o2o.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.PointRecord;
import com.o2o.common.model.User;
import com.o2o.service.PointRecordService;
import com.o2o.util.ApplicationProperties;
import com.o2o.util.HttpUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PointsRecordController extends Controller {

	static PointRecordService pointRecordService=new PointRecordService();
	public void index(){
	
		setAttr("title","积分记录列表");
		render("index.html");
	}
	public void tableData(){
		List<Record>list=pointRecordService.tableData();
		renderJson(pointRecordService.reload(list));
	}
	//记录状态改为1---兑换成功。向用户推送消息，减少用户积分
	//模板标题为：积分变更通知
	public void confirmOK(){
		Map<String,String> resultMap=new HashMap<>();
		String mobanurl=ApplicationProperties.get("wxTemplateUrl");
		String wxSendUrl=ApplicationProperties.get("wxSendUrl");
		String mobanresult=HttpUtils.doGet(mobanurl);
		if(StringUtils.isNotBlank(mobanresult)){
			JSONObject json=JSONObject.fromObject(mobanresult);
			JSONArray jsonarray=json.getJSONArray("template_list");
			String mobanid="";
			String title="您有一条消息请查收";
			for(int i=0;i<jsonarray.size();i++){
				JSONObject o1=(JSONObject) jsonarray.get(i);
				if(o1.getString("title").equals(title)){
					mobanid=o1.getString("template_id");
					break;
				}
			}
			if(StringUtils.isNotBlank(mobanid)){
				String pointRecordId=getPara("id");
				PointRecord pointrecord=PointRecord.dao.findById(pointRecordId);
				if(pointrecord!=null){
					String openId=pointrecord.getUserOpenid();
				    User user=User.dao.findById(openId);
				    if(user!=null){
				    	String type="message";
				    	String nickName=user.getUserNickname();
				    	String content="你的礼品已成功兑换";
				    	String messageurl=wxSendUrl+"?user_openid="+openId+"&templete_id="+mobanid+"&nickname="+nickName
				    			+"&type="+type+"&content="+content;
				    	String messageresult=HttpUtils.doGet(messageurl);
				    	if(messageresult.contains("TemplateSenderResult")){
				    	
				    		pointrecord.setPointRecordStatus(1);
				    		pointrecord.update();
				    		resultMap.put("status", "1");
				    		resultMap.put("content", "兑换成功");
				    	}else{
				    		resultMap.put("status", "2");
				    		resultMap.put("content", "消息发送失败");
				    	}
				    }else{
				    	resultMap.put("status", "5");
				    	resultMap.put("content", "用户查询失败");
				    }
				}else{
					resultMap.put("status", "4");
					resultMap.put("content", "订单信息不存在");
				}
			}else{
				resultMap.put("status", "3");
				resultMap.put("content", "模板获取失败");
			}
		}else{
			resultMap.put("status", "3");
			resultMap.put("content","模板获取失败");
		}
		renderJson(resultMap);
	}
}
