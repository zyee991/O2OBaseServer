package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class PointRecordService {

	public List<Record> tableData() {
		List<Record>list=Db.find("select a.*,u.user_nickname from tb_point_record a,tb_user u where a.user_openid=u.user_openid");
		return list;
	}

	public List<Record> reload(List<Record> list) {
	  
		for(Record record:list){
			if(record.get("point_from").equals(1)){
				record.set("point_from", "购买商品");
			}else if(record.get("point_from").equals(2)){
				record.set("point_from", "购买服务");
			}else {
				record.set("point_from", "消费商品");
			}
			
			if(record.get("point_record_status").equals(1)){
				record.set("point_record_status", "兑换成功");
			}else{
				record.set("point_record_status", "待确认");
			}
		}
		return list;
	}

}
