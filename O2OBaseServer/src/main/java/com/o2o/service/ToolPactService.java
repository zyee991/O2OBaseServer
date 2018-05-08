package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class ToolPactService {

	public List<Record> tableData(String tid) {
		String wheresql=" where t.mid=m.mid";
		List<Record>list=Db.find("select*from tb_pact_rent t,tb_muser m"+wheresql);
		return list;
	}

}
