package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.Announcement;

public class WxHeadService {

	public List<Record> tableData() {
		List<Record> list=Db.find("select*from tb_announcement");
		return list;
	}

	public List<Record> reload(List<Record> list) {
		for(Record record:list){
			if(record.get("announcement_on").equals(true)){
				record.set("ison", "显示");
			}else{
				record.set("ison","不显示");
			}
		}
		return list;
	}

	public void save(Announcement announcement) {
		try{
			announcement.save();
		}catch(Exception e){
			e.printStackTrace();
			announcement.update();
		}
		
	}

	public Announcement findById(String id) {
		return Announcement.dao.findById(id);
	}

	public void deleteById(String id) {
		Announcement.dao.deleteById(id);
	}

}
