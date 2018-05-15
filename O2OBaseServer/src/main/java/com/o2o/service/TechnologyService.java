package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.Technology;

public class TechnologyService {
	
	static Technology dao = new Technology().dao();

	public List<Record> tableData() {
		List<Record> list = Db.find("select * from tb_technology");
		reload(list);
		return list;
	}
	
	
	public void reload(List<Record> list) {
		for(Record record : list) {
			if("0".equals(record.get("fat_state"))) {
				record.set("state_name","未处理");
			} else if ("1".equals(record.get("fat_state"))) {
				record.set("state_name", "处理中");
			} else if ("2".equals(record.get("fat_state"))) {
				record.set("state_name", "处理完成");
			}
		}
	}

	public void deleteById(String id) {
		dao.deleteById(id);
	}


	public void save(Technology technology) {
		try{
			technology.save();
		} catch (Exception e) {
			technology.update();
		}
	}
}
