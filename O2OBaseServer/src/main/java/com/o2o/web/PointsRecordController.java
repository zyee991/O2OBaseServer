package com.o2o.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
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
		String mobanurl=ApplicationProperties.get("wxtTemplateUrl");
		String wxSendUrl=ApplicationProperties.get("wxSendUrl");
		String mobanresult=HttpUtils.doGet(mobanurl);
		if(StringUtils.isNotBlank(mobanresult)){
			JSONObject json=JSONObject.fromObject(mobanresult);
			JSONArray jsonarray=json.getJSONArray("template_list");
			String mobanid="";
			String title="积分变更通知";
			for(int i=0;i<jsonarray.size();i++){
				JSONObject o1=(JSONObject) jsonarray.get(i);
				if(o1.getString("title").equals("")){
					
				}
			}
		}
	}
}
