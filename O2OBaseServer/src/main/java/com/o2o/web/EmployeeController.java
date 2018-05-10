package com.o2o.web;

import java.util.List;
import java.util.UUID;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.RecruitInfo;
import com.o2o.service.EmployeeService;

public class EmployeeController extends Controller {
static EmployeeService employeeService=new EmployeeService();

//显示
	public void index(){
		setAttr("title","招聘信息");
		render("index.html"); 
	}
	public void tableData(){
		List<Record>list=employeeService.tableData();
		renderJson(list);
	}
	
	public void update(){
		setAttr("title","更改招聘信息");
		String id=getPara("id");
		RecruitInfo recruitInfo=employeeService.findById(id);
		setAttr("recruitInfo",recruitInfo);
		render("update.html");
	}
	
	public void view(){
		setAttr("title","查看招聘信息");
		String id=getPara("id");
		RecruitInfo recruitInfo=employeeService.findById(id);
		setAttr("recruitInfo",recruitInfo);
	}
	public void delete(){
		String id = getPara("id");
		employeeService.deleteById(id);
		renderJavascript("window.location.href='/employee'");
	}
	
	public void add(){
		setAttr("newId",UUID.randomUUID());
		render("add.html");
	}
	
	public void save(){
		RecruitInfo recruitinfo=getBean(RecruitInfo.class);
		employeeService.save(recruitinfo);
		renderJavascript("window.location.href='/employee'");
	}
}
