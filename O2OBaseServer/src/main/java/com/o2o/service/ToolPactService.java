package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class ToolPactService {

	public List<Record> tableData(String tid) {
		String wheresql=" where t.mid=m.mid where t.tid=o.tid and t.tid='"+tid+"'";
		List<Record>list=Db.find("select t.*,m.muser,o.name_t from tb_pact_rent t,tb_muser m,tb_rent_tools o"+wheresql);
		return list;
	}

	public List<Record> reload(List<Record> list) {
		for(Record record:list) {
			if(record.get("pre_state").equals(1) ) {
				record.set("pre_state", "待审批");
			} else if (record.get("pre_state").equals(2)) {
				record.set("pre_state", "待归还");
			}else if(record.get("pre_state").equals(3)){
				record.set("pre_state", "归还完成");
			}else{
				record.set("pre_state", "审批驳回");
			}

		}
		return list;
	}

}
