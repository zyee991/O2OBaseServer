package com.o2o.web;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Firsttype;
import com.o2o.service.GoodsTypeService;

public class GoodsTypeController extends Controller {
	
   static GoodsTypeService goodsTypeService=new GoodsTypeService();

   public void index(){
	   setAttr("title","商品类型");
	   Page<Firsttype> page=goodsTypeService.paginage(1,10);
	   setAttr("page",page);
	   render("index.html");
   }
}
