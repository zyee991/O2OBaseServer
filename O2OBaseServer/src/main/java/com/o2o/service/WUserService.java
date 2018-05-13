package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class WUserService {

	public List<Record> tableData() {
		List<Record>list=Db.find("select a.*,d.* from tb_user a,view_address d where a.district_id=d.district_id");
		return list;
	}

	public List<Record> reload(List<Record> list) {
		
		for(Record record:list){
			String districtname=record.getStr("province_name")+" "+record.getStr("city_name")+" "+record.getStr("district_name");
			record.set("districtname", districtname);
		}
		return list;
	}

}
