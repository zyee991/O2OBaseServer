package com.o2o.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.Shop;
import com.o2o.service.ShopService;

public class ShopController extends Controller {

	static ShopService shopService=new ShopService();
	public void index(){
		setAttr("title","库存管理");
		List<Record> shoplist=shopService.paginate1(1, 10);
		setAttr("list",shoplist);
		render("index.html");
	}
}
