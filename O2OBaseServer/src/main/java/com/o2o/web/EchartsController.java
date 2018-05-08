package com.o2o.web;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class EchartsController extends Controller{
	
	public void goodsCount() {
		String sql = "SELECT * FROM view_echarts_goods";
		List<Record> list = Db.find(sql);
		renderJson(list);
	} 
	
	public void serviceCount() {
		String sql = "SELECT * FROM view_echarts_service";
		List<Record> list = Db.find(sql);
		renderJson(list);
	}

}
