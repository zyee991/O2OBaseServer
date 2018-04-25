package com.o2o.web;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.joda.time.DateTime;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.Shop;
import com.o2o.service.ShopService;
import com.sun.tools.javac.util.Convert;

public class ShopController extends Controller {

	static ShopService shopService=new ShopService();
	public void index(){
		setAttr("title","库存管理");
		List<Record> shoplist=shopService.paginate1(1, 10);
		setAttr("list",shoplist);
		render("index.html");
	}
	
	public void add(){
	 setAttr("newId",UUID.randomUUID());
	 System.out.println(shopService.getGoodList().toString());
	 setAttr("goodslist",shopService.getGoodList());
	 render("add.html");
	}
	
	public void save(){
		Shop shop=new Shop();
		shop.setShopId(getPara("shopid"));
		shop.setShopCount(Integer.parseInt(getPara("number")));
		shop.setShopIstaocan(Boolean.valueOf(getPara("istaocan")));
		shop.setShopLot(Integer.parseInt(getPara("shoplot")));
		shop.setShopOn(Boolean.valueOf("shopon"));
		shop.setShopPrice(Float.parseFloat(getPara("shopprice")));
	    String date=getPara("endtime");
	    SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try {
	    	System.out.println(date);
			Date endtime=sf.parse(date);
			shop.setEndTime(endtime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shopService.save(shop);
		renderJavascript("window.location.href='/shop'");
	}
}
