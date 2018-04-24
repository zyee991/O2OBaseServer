package com.o2o.web;

import com.jfinal.core.Controller;
import com.o2o.service.ServiceOrderService;

public class ServiceOrderController extends Controller {

	static ServiceOrderService serviceOrderService=new ServiceOrderService();
	
	public void index(){
		setAttr("title","订单管理");
		render("index.html");
	}
}
