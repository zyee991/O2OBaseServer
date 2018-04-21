package com.o2o.web;

import com.jfinal.core.Controller;
import com.o2o.service.GoodsTypeService;

public class GoodsTypeController extends Controller {
	
   static GoodsTypeService goodsTypeService=new GoodsTypeService();

   public void index(){
	   setAttr("title","商品类型");
   }
}
