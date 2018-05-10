package com.o2o.web;

import java.util.List;
import java.util.UUID;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.City;
import com.o2o.common.model.District;
import com.o2o.common.model.Province;
import com.o2o.common.model.RentTools;
import com.o2o.service.ToolService;

public class ToolController extends Controller {

	static ToolService toolservice=new ToolService();
	
	public void index(){
	setAttr("title","工具租赁列表");
	render("index.html");
	}
	
	public void tableData(){
		List<Record>list=toolservice.tableData();
		renderJson(toolservice.reload(list));
	}
	public void add(){
		setAttr("newId",UUID.randomUUID());
		List<Province>provincelist=toolservice.getProvinceList();
		setAttr("provincelist",provincelist);
		render("add.html");
	}
	public void getcity(){
		String provinceId = getPara("provinceId");
		List<City> list = toolservice.findByProvinceId(provinceId);
		renderJson(list);
	}
	
	public void getarea(){
		String cityId=getPara("cityId");
		List<District> list=toolservice.findByCityId(cityId);
		renderJson(list);
	}
	
	public void update(){
		setAttr("title","编辑工具租赁消息");
		String id=getPara("id");
		RentTools tool=RentTools.dao.findById(id);
		List<Province>provincelist=toolservice.getProvinceList();
		setAttr("rentTools",tool);
		setAttr("provincelist",provincelist);
		String addressid=tool.getAddressTid();
		List<Record>list=toolservice.getAddressById(addressid);
		setAttr("list",list);
		render("update.html");
	}
	
	public void view(){
		setAttr("title","查看工具租赁信息");
		String id=getPara("id");
		RentTools tool=RentTools.dao.findById(id);
		String addressid=tool.getAddressTid();
		List<Record>list=toolservice.getAddressById(addressid);
		setAttr("list",list);
		setAttr("rentTools",tool);
		render("view.html");
	}
	
	public void delete(){
		String id=getPara("id");
		RentTools.dao.deleteById(id);
		renderJavascript("window.location.href='/tools'");
	}
}
