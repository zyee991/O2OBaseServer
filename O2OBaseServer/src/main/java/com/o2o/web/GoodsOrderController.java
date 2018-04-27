package com.o2o.web;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.service.OrderService;

public class GoodsOrderController extends Controller {

	static OrderService orderService=new OrderService();
	public void index(){
		setAttr("title","商品订单管理");
		List<Record>goodorderlist=orderService.paginate(1,10);
		System.out.println(goodorderlist);
		setAttr("goodorderlist",goodorderlist);
		render("index.html");
	}
}
