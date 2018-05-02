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
}
