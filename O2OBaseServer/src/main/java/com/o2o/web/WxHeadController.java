package com.o2o.web;

import java.util.List;
import java.util.UUID;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.Announcement;
import com.o2o.service.WxHeadService;

public class WxHeadController extends Controller {

	public static WxHeadService wxHeadService=new WxHeadService();
	public void index(){
		setAttr("title","微信首页列表");
		render("index.html");
	}
	public void tableData(){
		List<Record>list=wxHeadService.tableData();
		renderJson(wxHeadService.reload(list));
	}
	
	public void add(){
		setAttr("newId",UUID.randomUUID());
		render("add.html");
	}
	
	public void save(){
		Announcement announcement=getBean(Announcement.class);
		System.out.println(announcement);
		wxHeadService.save(announcement);
		renderJavascript("window.location.href='/WxHead'");
	}
}
