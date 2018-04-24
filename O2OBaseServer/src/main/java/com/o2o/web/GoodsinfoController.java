package com.o2o.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import com.o2o.common.model.Goodsinfo;
import com.o2o.common.model.Sectype;
import com.o2o.service.GoodsinfoService;
import com.o2o.util.FtpUtil;

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
			List<Sectype> typelist=goodsinfoService.getTypeList();
			
			setAttr("newId",UUID.randomUUID());
			setAttr("typelist",typelist);
			render("add.html");
		}
		
		//保存
		public void save() {
			Goodsinfo goodsinfo = getBean(Goodsinfo.class);
			System.out.println(getPara("goodsinfoId"));
			System.out.println(goodsinfo.getGoodsinfoId());
			UploadFile file = getFile("pic");
			if(file != null) {
				File f = file.getFile();
				try {
					InputStream in = new FileInputStream(f);
					String image = new FtpUtil().uploadFile("goodsinfo", new Date().getTime()+"", in);
					goodsinfo.setGoodsinfoImage(image);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
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
