package com.o2o.web;

import java.util.List;
import java.util.UUID;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.City;
import com.o2o.common.model.District;
import com.o2o.common.model.Province;
import com.o2o.common.model.RentFactory;
import com.o2o.service.FactoryService;

public class FactoryController extends Controller {

	public FactoryService factoryservice=new FactoryService();
	public void index(){
		setAttr("title","场地租赁");
		render("index.html");
	}
	
	public void tableData(){
		List<Record>list=factoryservice.tableData();
		renderJson(factoryservice.reload(list));
	}
	public void add(){
		setAttr("newId",UUID.randomUUID());
		List<Province>provincelist=factoryservice.getProvinceList();
		setAttr("provincelist",provincelist);
		render("add.html");
	}
	
	public void getcity(){
		String provinceId = getPara("provinceId");
		List<City> list = factoryservice.findByProvinceId(provinceId);
		renderJson(list);
	}
	
	public void getarea(){
		String cityId=getPara("cityId");
		List<District> list=factoryservice.findByCityId(cityId);
		renderJson(list);
	}
	
	public void save(){
		RentFactory rentfactory=getBean(RentFactory.class);
		System.out.println(rentfactory.toJson());
		factoryservice.save(rentfactory);
		renderJavascript("window.location.href='/factory'");
	}
	
	public void update(){
		String id=getPara("id");
		List<Province>provincelist=factoryservice.getProvinceList();
		setAttr("provincelist",provincelist);
		List<Record>list=factoryservice.findById(id);
		setAttr("list",list);
		render("update.html");
	}
}
