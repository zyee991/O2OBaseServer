package com.o2o.web;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.service.ServiceOrderService;

public class ServiceOrderController extends Controller {

	static ServiceOrderService serviceOrderService=new ServiceOrderService();
	
	public void index(){
		setAttr("title","服务订单管理");
		render("index.html");
	}
	
	public void tableData() {
		List<Record> list = serviceOrderService.tableData();
		System.out.println(list);
		renderJson(serviceOrderService.reload(list));
	}
	
	public void dispatch(){
		String id=getPara("id");
		String isforward=getPara("is_forward");
		System.out.println(id+"----------------------"+isforward);
		render("dispatch.html");
	}
}
