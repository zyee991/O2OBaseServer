package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class MUserService {

	public List<Record> tableData() {
		List<Record>list=Db.find("select*from tb_muser");
		return list;
	}

	public List<Record> reload(List<Record> list) {
		
		for(Record record:list){
			if(new Boolean(false).equals(record.get("order_state"))){
				record.set("order_state", "停止接单");
			}else {
				record.set("order_state","正在接单");
			}
		}
		for(Record record:list){
			if("B".equals(record.get("m_type"))){
				record.set("m_type", "厂内技师");
			}else{
				record.set("m_type", "厂外技师");
			}
		}
		return list;
	}

	
}
