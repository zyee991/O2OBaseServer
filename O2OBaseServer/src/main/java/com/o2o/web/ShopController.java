package com.o2o.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.Sectype;
import com.o2o.common.model.Shangjiaoperation;
import com.o2o.common.model.Shop;
import com.o2o.common.model.Taocan;
import com.o2o.service.ShopService;

public class ShopController extends Controller {

	static ShopService shopService = new ShopService();
	
	public void index() {
		setAttr("title", "库存管理");
		render("index.html");
	}

	public void tableData() {
		List<Record> list = shopService.tableData();
		renderJson(list);
	}

	public void add() {
		setAttr("newId", UUID.randomUUID());
		List<Sectype> typelist = shopService.getTypeList();
		setAttr("typelist", typelist);
		render("add.html");
	}

	public void save() {
		Shop shop = new Shop();
		Shangjiaoperation shangjiaoperation = new Shangjiaoperation();
		shangjiaoperation.setGoodsinfoId(getPara("goodsname"));
		shangjiaoperation.setShopId(getPara("shopid"));
		shangjiaoperation.setShopGoodsId(UUID.randomUUID().toString());
		
		shop.setShopId(getPara("shopid"));
		shop.setShopCount(Integer.parseInt(getPara("number")));
		shop.setShopIstaocan(Boolean.valueOf(getPara("istaocan")));
		shop.setShopLot(Integer.parseInt(getPara("shoplot")));
		shop.setShopOn(Integer.parseInt(getPara("shopon")));
		shop.setShopPrice(Float.parseFloat(getPara("shopprice")));
		String date = getPara("start_time");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date endtime = sf.parse(date);
			shop.setEndTime(endtime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		shopService.save(shop, shangjiaoperation);
		List<Taocan> taocanList = shopService.findTaocanListByGoodsinfoId(shangjiaoperation.getGoodsinfoId());
		for(Taocan taocan:taocanList) {
			taocan.setTaoShopid(shop.getShopId());
			taocan.update();
		}
		renderJavascript("window.location.href='/shop'");
	}

	public void update() {
		String id = getPara("id");
		List<Record> shoplist = shopService.findById(id);
		setAttr("shoplist", shoplist);
		render("update.html");
	}

	public void view() {
		setAttr("title", "查看库存信息");
		String id = getPara("id");
		List<Record> shoplist = shopService.findById(id);
		setAttr("shoplist", shoplist);
		render("view.html");
	}

	public void delete() {
		String id = getPara("id");
		String shopgoodsid = getPara("shopgoodsid");
		shopService.deleteById(id, shopgoodsid);
		redirect("/shop");
	}

	public void getGoods() {
		String typeId = getPara("typeId");
		List<Record> list = shopService.findByTypeId(typeId);
		renderJson(list);
	}

	public void getGoodsById() {
		String goodsid = getPara("goodsid");
		List<Record> goodsinfo = shopService.findGoodById(goodsid);
		renderJson(goodsinfo);
	}

}
