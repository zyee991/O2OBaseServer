package com.o2o.web;

import java.util.List;
import java.util.UUID;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.Goodsinfo;
import com.o2o.common.model.Sectype;
import com.o2o.service.GoodsinfoService;

public class GoodsinfoController extends Controller {

	static GoodsinfoService goodsinfoService = new GoodsinfoService();

	// 显示
	public void index() {
		setAttr("title", "商品基本信息");
		render("index.html");
	}
    
	public void tableData(){
		List<Record>list=goodsinfoService.tableData();
		renderJson(list);
	}
	// 查看
	public void view() {
		setAttr("title","查看商品基本信息");
		String id = getPara("id");
		List<Record> list = goodsinfoService.findGoodListById(id);
		setAttr("goodsinfo", list);
		render("view.html");
	}

	// 编辑
	public void update() {
		setAttr("title", "商品基本信息");
		String id = getPara("id");
		Goodsinfo goodsinfo = goodsinfoService.findById(id);
		setAttr("goodsinfo", goodsinfo);
		
		List<Sectype> typelist = goodsinfoService.getTypeList();
		setAttr("typelist", typelist);

		render("update.html");
	}

	// 删除
	public void delete() {
		String id = getPara("id");
		goodsinfoService.deleteById(id);
		renderJavascript("window.location.href='/goods_info'");
	}

	// 添加
	public void add() {
		List<Sectype> typelist = goodsinfoService.getTypeList();
		setAttr("newId", UUID.randomUUID());
		setAttr("typelist", typelist);
		render("add.html");
	}

	// 保存
	public void save() {
		Goodsinfo goodsinfo = getBean(Goodsinfo.class);
		goodsinfoService.save(goodsinfo);
		renderJavascript("window.location.href='/goods_info'");
	}

	//
	public void getTypeList() {
		List<Sectype> typelist = goodsinfoService.getTypeList();
		setAttr("typelist", typelist);
		renderJson();
	}

}
