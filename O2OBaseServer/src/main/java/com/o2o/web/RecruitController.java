package com.o2o.web;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.service.RecruitService;

public class RecruitController extends Controller {

	RecruitService recruitService=new RecruitService();
	public void index(){
		setAttr("title","应聘者列表");
		List<Record> employerlist=recruitService.paginate(getParaToInt(0, 1), 10);
		setAttr("employerlist",employerlist);
		render("index.html");
	}
}
