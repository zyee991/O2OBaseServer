package com.o2o.web;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import com.o2o.common.model.Goodsinfo;
import com.o2o.common.model.Sectype;
import com.o2o.service.GoodsinfoService;
import com.o2o.util.CommonUtils;

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
			List<Sectype> typelist=goodsinfoService.getTypeList();
			if(id!=null){
			Goodsinfo goodsinfo = goodsinfoService.findById(id);
			setAttr("goodsinfo",goodsinfo);
			}
			setAttr("newId",UUID.randomUUID());
			setAttr("date",CommonUtils.sdf.format(new Date()));
			setAttr("typelist",typelist);
			render("add.html");
		}
		
		//保存
		public void save() {
			Goodsinfo goodsinfo = getBean(Goodsinfo.class);
			UploadFile uf=getFile(getPara("file"));
			String filename=uf.getFileName();
			System.out.println(filename+"-------------------------");
			goodsinfo.setGoodsinfoImage(filename);
			goodsinfoService.save(goodsinfo);
//			redirect("/manager");
			renderJavascript("window.location.href='/goods_info'");
		}
		
		//
		public void getTypeList(){
			List<Sectype> typelist=goodsinfoService.getTypeList();
			setAttr("typelist",typelist);
			renderJson();
		}
		
}
