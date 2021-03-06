package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.RecruitInfo;

public class EmployeeService {

	private static final RecruitInfo dao=new RecruitInfo().dao();
	public Page<RecruitInfo> paginage(int i, int j) {
	return dao.paginate(i,j, "select*","from tb_recruit_info");
	}
	public void save(RecruitInfo recruitinfo) {
		try{
			recruitinfo.save();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public RecruitInfo findById(String id) {
		return dao.findById(id);

	}
	public void deleteById(String id) {
		dao.deleteById(id);
		
	}
	public List<Record> tableData() {
		List<Record>list=Db.find("select*from tb_recruit_info");
		return list;
	}

	
}
