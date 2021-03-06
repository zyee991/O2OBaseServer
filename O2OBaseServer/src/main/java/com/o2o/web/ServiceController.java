package com.o2o.web;

import java.util.List;
import java.util.UUID;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.Sectype;
import com.o2o.common.model.Service;
import com.o2o.service.ServiceService;

public class ServiceController extends Controller {

	static ServiceService serviceService=new ServiceService();
	
	public void index(){
		setAttr("title","服务管理");
		render("index.html");
	}
	
	public void tableData(){
		List<Record>list=serviceService.tableData();
		renderJson(list);
	}
	public void add(){
		List<Sectype> typelist=serviceService.getTypeList();
		setAttr("typelist", typelist);
		setAttr("newId",UUID.randomUUID());
		render("add.html");
	}
	
	public void save(){
		Service service=getBean(Service.class);
	    serviceService.save(service);
	    renderJavascript("window.location.href='/service'");
	}
	
	public void update(){
		setAttr("title","服务基本信息");
		String id=getPara("id");
		Service service=serviceService.findById(id);
		List<Sectype> typelist = serviceService.getTypeListById(service.getSecTypeId());
		setAttr("typelist",typelist);
		setAttr("service",service);
		render("update.html");
	}
	
	public void view(){
		setAttr("title","查看服务基本信息");
		String id=getPara("id");
		List<Record> service=serviceService.findServiceById(id);
		setAttr("service",service);
		render("view.html");
	}
	public void delete(){
		String id=getPara("id");
		serviceService.deleteById(id);
		redirect("/service");
	}
	
}
