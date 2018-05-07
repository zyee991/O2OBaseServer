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
		setAttr("title","场地租赁记录列表");
		render("index.html");
	}
	
	public void tableData(){
		String id=getPara("id");
		List<Record>list=factoryPactService.tableData(id);
		renderJson(factoryPactService.reload(list));
	}
}
