package com.o2o.web;

import java.util.List;
import java.util.UUID;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.PointsGift;
import com.o2o.service.PointGoodsService;

public class PointGoodsController extends Controller {

	static PointGoodsService pointGoodsService=new PointGoodsService();
	public void index(){
		setAttr("title","积分礼物管理");
		render("index.html");
	}
	
	public void tableData(){
		List<Record>list=pointGoodsService.tableData();
		renderJson(list);
	}
	
	public void add(){
		setAttr("title","添加积分礼物");
		setAttr("newId",UUID.randomUUID());
		render("add.html");
	}
	
	public void save(){
		PointsGift pointsGift=getBean(PointsGift.class);
		pointsGift.save();
		renderJavascript("window.location.href='/pointsgoods'");
	}
	
	public void modify() {
		PointsGift pointsGift=getBean(PointsGift.class);
		pointsGift.update();
		renderJavascript("window.location.href='/pointsgoods'");
	}
	
	public void update(){
		String id=getPara("id");
		setAttr("title","编辑积分礼物");
		PointsGift pointsGift=PointsGift.dao.findById(id);
		setAttr("pointsGift",pointsGift);
		render("update.html");
	}
	
	public void view(){
		String id=getPara("id");
		PointsGift pointsGift=PointsGift.dao.findById(id);
		setAttr("pointsGift",pointsGift);
		render("view.html");
	}
	
	public void delete(){
		String id = getPara("id");
		pointGoodsService.deleteById(id);
		renderJavascript("window.location.href='/pointsgood'");
	}
}
