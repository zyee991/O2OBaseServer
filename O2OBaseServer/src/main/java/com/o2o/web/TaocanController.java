package com.o2o.web;

import java.util.List;
import java.util.UUID;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Goodsinfo;
import com.o2o.common.model.Sectype;
import com.o2o.service.GoodsinfoService;

public class TaocanController extends Controller {

	static GoodsinfoService goodsinfoService = new GoodsinfoService();

	// 显示
	public void index() {
		setAttr("title", "套餐信息");
		Page<Goodsinfo> page = goodsinfoService.paginateTaocan(1, 10);
		setAttr("page", page);
		render("index.html");
	}

	// 查看
	public void view() {
		String id = getPara("id");
		Goodsinfo goodsinfo = goodsinfoService.findById(id);
		setAttr("goodsinfo", goodsinfo);
		render("view.html");
	}

	// 编辑
	public void update() {
		setAttr("title", "套餐信息");
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
    System.out.println(typelist.toString());
		setAttr("newId", UUID.randomUUID());
		setAttr("typelist", typelist);
		render("add.html");
	}

	// 保存
	public void save() {
		Goodsinfo goodsinfo = getBean(Goodsinfo.class);
		goodsinfoService.save(goodsinfo);
		renderJavascript("window.location.href='/taocan'");
	}

	//
	public void getTypeList() {
		List<Sectype> typelist = goodsinfoService.getTypeList();
		System.out.println(typelist.toString());
		setAttr("typelist", typelist);
		renderJson();
	}
	
	public void getgoodsList(){
		System.out.println(getPara("type_id"));
		renderJson();
	}

}
