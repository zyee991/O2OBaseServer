package com.o2o.web;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.service.MUserService;

public class MUserController extends Controller {

	static MUserService muserService=new MUserService();
	public void index(){
		setAttr("title","技师管理列表");
		render("index.html");
	}
	
	public void tableData(){
		List<Record>list=muserService.tableData();
		renderJson(muserService.reload(list));
	}
}
