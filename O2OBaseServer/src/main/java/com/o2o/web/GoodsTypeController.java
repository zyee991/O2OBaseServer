package com.o2o.web;

import java.util.Date;
import java.util.UUID;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Firsttype;
import com.o2o.common.model.Sectype;
import com.o2o.service.GoodsTypeService;
import com.o2o.util.CommonUtils;

public class GoodsTypeController extends Controller {
	
   static GoodsTypeService goodsTypeService=new GoodsTypeService();

   public void index(){
	   setAttr("title","商品类型");
	   Page<Firsttype> page=goodsTypeService.paginage(1,10);
	   setAttr("page",page);
	   render("index.html");
   }
   
   public void add(){
	   String id=getPara("id");
	   if(id!=null){
		 Firsttype firsttype=goodsTypeService.findById(id);
		 setAttr("firsttype",firsttype);
	   }
	   setAttr("newId",UUID.randomUUID());
	   setAttr("date",CommonUtils.sdf.format(new Date()));
	   render("add.html");
   }
   
   public void save(){
	   String id=getPara("first_type_id");
	   if(id!=null){
		Sectype sectype=getBean(Sectype.class);
		goodsTypeService.saveSecondType(sectype);
	   }else{
		   System.out.println(id+"--------------------------------");
		   Firsttype firsttype=getBean(Firsttype.class);
		   goodsTypeService.saveFirstType(firsttype);
	   }
   }
}
