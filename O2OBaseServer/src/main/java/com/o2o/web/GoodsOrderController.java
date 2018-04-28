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
	
	//商品发货，支付状态不变（1---支付成功），0---未支付，1----支付成功，2------要求退款，3-----退款成功
	//商品订单状态改为（1-----商家发货），0----商家未处理，1-----商家发货，2----确认收货
	public void forward(){
		System.out.println("发货了------------");
	String id=getPara("id");
	//商家确认发货
	int status=1;
	setAttr("message","商家确认发货");
	renderJavascript("window.location.href='/goods_order'");
	}
}
