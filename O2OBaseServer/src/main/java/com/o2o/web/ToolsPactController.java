package com.o2o.web;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.service.ToolPactService;

public class ToolsPactController extends Controller {

	private static ToolPactService toolPactService=new ToolPactService();
	
	public void index(){
		String tid=getPara("id");
		setAttr("title","工具租赁记录");
		render("index.html");
	}
	
	public void tableData(String tid){
		String id=getPara("id");
		List<Record>list=toolPactService.tableData(tid);
	}
}
