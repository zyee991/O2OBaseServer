package com.o2o.web;

import java.util.Date;
import java.util.UUID;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Goodsinfo;
import com.o2o.common.model.Manager;
import com.o2o.service.GoodsinfoService;
import com.o2o.util.CommonUtils;
import com.o2o.util.SecurityAuthentication;

public class GoodsinfoController extends Controller {

	static GoodsinfoService goodsinfoService=new GoodsinfoService();
	//显示
	public void index(){
		setAttr("title","商品基本信息");
		Page <Goodsinfo> page=goodsinfoService.paginate(1, 10);
		setAttr("page",page);
		render("index.html");
	}
	
	//查看
	public void view(){
		String id=getPara("id");
		Goodsinfo goodsinfo=goodsinfoService.findById(id);
		setAttr("goodsinfo",goodsinfo);
		render("view.html");
	}
	
	//编辑
		public void update(){
			String id=getPara("id");
			Goodsinfo goodsinfo=goodsinfoService.findById(id);
			setAttr("goodsinfo",goodsinfo);
			render("update.html");
		}
		
		//删除
		public void delete (){
			String id=getPara("id");
			Goodsinfo goodsinfo=goodsinfoService.deleteById(id);
			renderJavascript("window.location.href='/goods_info'");
		}
		
		//添加
		public void add() {
			String id = getPara("id");
			if(id!=null){
			Goodsinfo goodsinfo = goodsinfoService.findById(id);
			setAttr("goodsinfo",goodsinfo);
			}
			setAttr("newId",UUID.randomUUID());
			setAttr("date",CommonUtils.sdf.format(new Date()));
			render("add.html");
		}
		
		//保存
		public void save() {
			Goodsinfo goodsinfo = getBean(Goodsinfo.class);
			goodsinfoService.save(goodsinfo);
//			redirect("/manager");
			renderJavascript("window.location.href='/manager'");
		}	
}
