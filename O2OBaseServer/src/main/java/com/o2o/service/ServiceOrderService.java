package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class ServiceOrderService {

	public List<Record> tableData() {
		List<Record>list=Db.find("select a.* from v_service_order a");
		return list;
	}

	public List<Record> reload(List<Record> list) {
		for(Record record:list){
			if(record.get("service_order_status").equals(0)&&record.get("service_pay_status").equals(1)){
				record.set("status_name", "待派单");
			}else if(record.get("service_order_status").equals(1)){
				record.set("status_name", "待验收");
			}else if(record.get("service_order_status").equals("2")){
				record.set("status_name", "待退款");
			}else {
				record.set("status_name","已完成");
			}
		}
		return list;
	}

}
 