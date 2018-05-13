package com.o2o.web;

import java.util.List;
import java.util.UUID;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.City;
import com.o2o.common.model.District;
import com.o2o.common.model.Goodsinfo;
import com.o2o.common.model.Province;
import com.o2o.common.model.Realshop;
import com.o2o.common.model.Sectype;
import com.o2o.common.model.Taocan;
import com.o2o.service.RealShopService;

public class RealShopController extends Controller {

	static RealShopService realShopService=new RealShopService();
	
	public void index(){
		setAttr("title","门店管理");
		render("index.html");
	}
	
	public void tableData(){
		List<Record>list=realShopService.tableData();
		renderJson(list);
	}
	public void add(){
		setAttr("newId",UUID.randomUUID());
		List<Province> provincelist=realShopService.getProvinceList();
		List<Sectype> typelist=realShopService.getTypeList();
		setAttr("typelist",typelist);
		setAttr("provincelist",provincelist);
		render("add.html");
	}
	
	public void getcity(){
		String provinceId = getPara("provinceId");
		List<City> list = realShopService.findByProvinceId(provinceId);
		renderJson(list);
	}
	
	public void getarea(){
		String cityId=getPara("cityId");
		List<District> list=realShopService.findByCityId(cityId);
		renderJson(list);
	}
	public void save() {
		 Realshop realshop=getBean(Realshop.class);
		 System.out.println(realshop.toJson());
		 realShopService.save(realshop);
		renderJavascript("window.location.href='/realshop'");
	}
	
	public void update(){
		setAttr("title","门店信息");
		String shpid=getPara("id");
		Realshop realShop=Realshop.dao.findById(shpid);		
		String district=realShop.getDistrictId();
		String typeid=getPara("typeid");
		List<Record> dictlist=realShopService.getdictlist(district);
	/*	List<Record> typelist=realShopService.getTypeList(typeid);*/
		List<Province> provincelist=realShopService.getProvinceList();
		List<Sectype> typelist=realShopService.getTypeList();
		String sectypeid=realShop.getRealshopType();
		Sectype sectype=Sectype.dao.findById(sectypeid);
		String sectype_name=sectype.getSecTypeName();
		setAttr("sectype_name",sectype_name);
		setAttr("typelist",typelist);
		setAttr("provincelist",provincelist);
		setAttr("dictlist",dictlist);
		System.out.println(dictlist+"----------------");
		Realshop realshop=realShopService.getshopById(shpid);
		setAttr("realshop",realshop);
		render("update.html");
	}
	
	public void delete(){
		String id=getPara("id");
		realShopService.deleteById(id);
		renderJavascript("window.location.href='/realshop'");
	}
	
}
