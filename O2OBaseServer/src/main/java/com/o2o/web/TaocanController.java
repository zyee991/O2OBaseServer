package com.o2o.web;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.Goodsinfo;
import com.o2o.common.model.Sectype;
import com.o2o.common.model.Taocan;
import com.o2o.service.GoodsinfoService;

public class TaocanController extends Controller {

	static GoodsinfoService goodsinfoService = new GoodsinfoService();

	// 显示
	public void index() {
		setAttr("title", "套餐信息");
		Page<Goodsinfo> page = goodsinfoService.paginateTaocan(getParaToInt(0, 1), 10);
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
		
		List<Record> taocanList = goodsinfoService.findTaocanByTaocanId(goodsinfo.getGoodsinfoId());
		setAttr("detailList",taocanList);
		System.out.println(taocanList);

		render("update.html");
	}

	// 删除
	public void delete() {
		String id = getPara("id");
		goodsinfoService.deleteById(id);
		renderJavascript("window.location.href='/taocan'");
	}

	// 添加
	public void add() {
		List<Sectype> typelist = goodsinfoService.getTypeListForTaocanSelect();
		setAttr("newId", UUID.randomUUID());
		setAttr("typelist", typelist);
		render("add.html");
	}

	// 保存
	public void save() {
		Goodsinfo goodsinfo = getBean(Goodsinfo.class);
		goodsinfoService.save(goodsinfo);
		String goodsDetail = getPara("goodsDetail");
		if(!"".equals(goodsDetail)) {
			String[] goodsArray = goodsDetail.split(",");
			for(String goods:goodsArray) {
				if(!"".equals(goods)) {
					Taocan taocan = new Taocan();
					String taocanId = goodsinfo.getGoodsinfoId();
					String goodsinfoId = goods.split(":")[0];
					String itemCount = goods.split(":")[1];
					String id = UUID.randomUUID().toString();
					taocan.setTaoGoodId(id);
					taocan.setTaocanId(taocanId);
					taocan.setGoodsinfoId(goodsinfoId);
					taocan.setItemCount(Integer.valueOf(itemCount));
					taocan.save();
				}
			}
		}
		renderJavascript("window.location.href='/taocan'");
	}
	
	public void getGoods(){
		String typeId = getPara("typeId");
		List<Goodsinfo> list = goodsinfoService.findByTypeId(typeId);
		renderJson(list);
	}

}
