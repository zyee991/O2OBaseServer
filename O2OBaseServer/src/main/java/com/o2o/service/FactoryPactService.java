package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class FactoryPactService {

	public List<Record> tableData(String id) {
		String wheresql=" where m.mid=a.mid and pid='"+id + "'";
		List<Record>list=Db.find("select a.*,m.muser from tb_pact_factory a,tb_muser m"+wheresql);
		return list;
	}

	//待审批---0   1----待归还    2----归还完成   
	public void confirm(){
		
   }
	public List<Record> reload(List<Record>list){
		for(Record record:list){
			if(record.get("pfa_state").equals(1)){
				record.set("status_name", "待审批");
			}else if(record.get("pfa_state").equals(2)){
				record.set("status_name", "待归还");
			}else if(record.get("pfa_state").equals(3)){
				record.set("status_name", "归还完成");
			}else{
				record.set("status", "审批驳回");
			}
		}
		return list;
	}
}
