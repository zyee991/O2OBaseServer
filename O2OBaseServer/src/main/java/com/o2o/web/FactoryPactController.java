package com.o2o.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.PactEmploy;
import com.o2o.common.model.PactFactory;
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
	
	public void confirmOK(){
		String status=getPara("status");
		String pfaid=getPara("pfaid");
		String pid=getPara("pid");
		Date date=new Date();
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String time=df2.format(date);
		PactFactory pactfactory=PactFactory.dao.findById(pfaid);
		pactfactory.setPfaState(Integer.parseInt(status));
		pactfactory.setPfaEtime(date);
		renderJavascript("window.location.href='/recruit?pid='"+pid);
	}
}
