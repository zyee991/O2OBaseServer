package com.o2o.web;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Sectype;
import com.o2o.common.model.Service;
import com.o2o.service.ServiceService;
import com.o2o.util.CommonUtils;

public class ServiceController extends Controller {

	static ServiceService serviceService=new ServiceService();
	
	public void index(){
		setAttr("title","服务管理");
		Page<Service> page=serviceService.paginage(1,10);
		setAttr("page",page);
		render("index.html");
	}
	
	public void add(){
		List<Sectype> typelist=serviceService.getTypeList();
		System.out.println(typelist.toString());
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
		setAttr("service",service);
		render("update.html");
	}
	
	public void delete(){
		String id=getPara("id");
		serviceService.deleteById(id);
		redirect("/service");
	}
}
