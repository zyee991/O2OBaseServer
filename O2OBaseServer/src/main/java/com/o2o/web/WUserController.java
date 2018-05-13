package com.o2o.web;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.service.WUserService;

public class WUserController extends Controller {

	static WUserService wuserService=new WUserService();
	public void index(){
		setAttr("title","客户管理列表");
		render("index.html");
	}
	
	public void tableData(){
		List<Record>list=wuserService.tableData();
		renderJson(wuserService.reload(list));
	}
}
