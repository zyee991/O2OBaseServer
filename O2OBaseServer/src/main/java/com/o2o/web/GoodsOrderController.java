package com.o2o.web;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.service.OrderService;

public class GoodsOrderController extends Controller {

	static OrderService orderService=new OrderService();
	public void index(){
		setAttr("title","商品订单管理");
		String paystatus=getPara("order_pay_status");
		String orderstatus=getPara("order_status");
		String anotherstatus=getPara("another_status");
		System.out.println(paystatus+"---------------"+orderstatus);
		List<Record>goodorderlist=orderService.paginate(getParaToInt(0, 1), 10,paystatus,orderstatus,anotherstatus);
		System.out.println(goodorderlist);
		setAttr("goodorderlist",goodorderlist);
		render("index.html");
	}
}
