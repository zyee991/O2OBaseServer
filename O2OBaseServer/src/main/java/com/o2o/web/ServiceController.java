package com.o2o.web;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Service;
import com.o2o.service.ServiceService;

public class ServiceController extends Controller {

	static ServiceService service=new ServiceService();
	
	public void index(){
		setAttr("title","服务管理");
		Page<Service> page=service.paginage(1,10);
		setAttr("page",page);
		render("index.html");
	}
	
	public void add(){
		
	}
}
