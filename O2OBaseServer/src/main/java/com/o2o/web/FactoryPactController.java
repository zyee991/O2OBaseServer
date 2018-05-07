package com.o2o.web;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.service.FactoryPactService;

public class FactoryPactController extends Controller {

	FactoryPactService factoryPactService=new FactoryPactService();
	public void index(){
		String id=getPara("id");
		setAttr("id",id);
		setAttr("title","场地租赁信息");
		render("index.html");
	}
	
	public void tableData(){
		List<Record>list=factoryPactService.tableData();
		renderJson(factoryPactService.reload(list));
	}
}
