package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class RecruitService {

	public List<Record>paginate(int pageNumber,int pageSize){
		Page<Record> page=Db.paginate(pageNumber, pageSize, "select a.*,m.user","from tb_pact_employ a,tb_muser m where a.mid=m.mid");
		return page.getList();
	}

	public List<Record> tableData(String id) {
		List<Record>list=Db.find("select a.*,m.muser from tb_pact_employ a,tb_muser m where a.mid=m.mid and rid=?",id);
		return list;
	}

	public List<Record> reload(List<Record> list) {
		for(Record record:list){
			if(record.get("pe_state").equals(1)){
				record.set("state", "待审批");
			}else if(record.get("pe_state").equals(2)){
				record.set("state", "审批通过");
			}else{
				record.set("state", "审批不通过");
			}
		}
		return list;
	}
}
