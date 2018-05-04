package com.o2o.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.Shangjiaoperation;
import com.o2o.common.model.Shop;
import com.o2o.service.ShopService;

public class ShopController extends Controller {

	static ShopService shopService=new ShopService();
	public void index(){
		setAttr("title","库存管理");
		Page<Record> page=shopService.paginate1(getParaToInt(0, 1), 10);
		setAttr("page",page);
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
		Shangjiaoperation shangjiaoperation=new Shangjiaoperation();
		shangjiaoperation.setGoodsinfoId(getPara("goodsname"));
		shangjiaoperation.setShopId(getPara("shopid"));
		shangjiaoperation.setShopGoodsId(UUID.randomUUID().toString());
		shop.setShopId(getPara("shopid"));
		System.out.println(getPara("number"));
		shop.setShopCount(Integer.parseInt(getPara("number")));
		shop.setShopIstaocan(Boolean.valueOf(getPara("istaocan")));
		shop.setShopLot(Integer.parseInt(getPara("shoplot")));
		shop.setShopOn(Integer.parseInt(getPara("shopon")));
		shop.setShopPrice(Float.parseFloat(getPara("shopprice")));
	    String date=getPara("start_time");
	    SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try {
	    	System.out.println(date);
			Date endtime=sf.parse(date);
			shop.setEndTime(endtime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shopService.save(shop,shangjiaoperation);
		renderJavascript("window.location.href='/shop'");
	}
	
	public void update(){
		String id=getPara("id");
		List<Record> shoplist=shopService.findById(id);
		System.out.println(shoplist.toString());
		setAttr("shoplist",shoplist);
		render("update.html");
	}
	
	public void delete(){
		String id=getPara("id");
		String shopgoodsid=getPara("shopgoodsid");
		shopService.deleteById(id,shopgoodsid);
		redirect("/shop");
	}
}
