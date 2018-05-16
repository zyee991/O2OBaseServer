package com.o2o.web;

import java.util.List;
import java.util.UUID;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Firsttype;
import com.o2o.common.model.Sectype;
import com.o2o.service.GoodsTypeService;

public class GoodsTypeController extends Controller {

	static GoodsTypeService goodsTypeService = new GoodsTypeService();

	public void index() {
		setAttr("title", "商品类型");
		Page<Firsttype> page = goodsTypeService.paginage(getParaToInt(0, 1), 10);
		setAttr("page", page);
		render("index.html");
	}

	public void add() {
		String id = getPara("firstid");
		if (id != null) {
			Firsttype firsttype = goodsTypeService.findById(id);
			setAttr("firsttype", firsttype);
		}
		setAttr("newId", UUID.randomUUID());
		render("add.html");
	}

	public void save() {
		String firstid = getPara("firsttTypeId");
		String name = getPara("typename");
		String id = getPara("newId");
		if (firstid != null && !"".equals(firstid)) {
			Sectype sectype = new Sectype();
			sectype.setFirstTypeId(firstid);
			sectype.setSecTypeId(id);
			sectype.setSecTypeName(name);
			goodsTypeService.saveSecondType(sectype);
		} else {
			Firsttype firsttype = new Firsttype();
			firsttype.setFirstTypeId(id);
			firsttype.setFirstTypeName(name);
			goodsTypeService.saveFirstType(firsttype);
		}
		renderJavascript("window.location.href='/goods_type'");
	}

	public void showSub() {
		String id = getPara("id");
		List<Sectype> list = goodsTypeService.findChildNavigationByParentId(id);
		renderJson(list);
	}

	public void update() {
		String firstid = getPara("firstid");
		String secid = getPara("secid");
		if (firstid != null) {
			Firsttype firsttype = goodsTypeService.findById(firstid);
			setAttr("name", firsttype.getFirstTypeName());
			setAttr("newId", firsttype.getFirstTypeId());
			render("update.html");
		} else {
			Sectype sectype = goodsTypeService.findChildById(secid);
			setAttr("name", sectype.getSecTypeName());
			setAttr("firstid", sectype.getFirstTypeId());
			setAttr("newId", sectype.getSecTypeId());
			render("update.html");
		}
	}

	public void delete() {
		String firstid = getPara("firstid");
		String secid = getPara("secid");
		if (firstid != null) {
			goodsTypeService.deleteFirstById(firstid);
		} else {
			goodsTypeService.deleteSecById(secid);
		}
	}
}
