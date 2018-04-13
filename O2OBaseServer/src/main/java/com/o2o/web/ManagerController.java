package com.o2o.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Manager;
import com.o2o.common.model.Navigation;
import com.o2o.service.ManagerRoleService;
import com.o2o.service.ManagerService;
import com.o2o.util.CommonUtils;
import com.o2o.util.SecurityAuthentication;
import com.o2o.validator.NavigationValidator;

public class ManagerController extends Controller{
	
	static ManagerService managerService = new ManagerService();
	static ManagerRoleService managerRoleService = new ManagerRoleService();
	
	public void index() {
		setAttr("title","用户管理");
		Page<Manager> page = managerService.paginate(1, 10);
		setAttr("page",page);
		render("index.html");
	}
	
	public void view() {
		String id = getPara("id");
		Manager manager = managerService.findById(id);
		setAttr("manager",manager);
		render("view.html");
	}
	
	public void add() {
		String id = getPara("id");
		if(id!=null){
		Manager manager = managerService.findById(id);
		setAttr("manager",manager);
		}
		setAttr("newId",UUID.randomUUID());
		setAttr("date",CommonUtils.sdf.format(new Date()));
		render("add.html");
	}
	
	public void save() {
		Manager manager = getBean(Manager.class);
		String password = SecurityAuthentication.crypt(manager.getPassword());
		manager.setPassword(password);
		managerService.save(manager);
//		redirect("/manager");
		renderJavascript("window.location.href='/manager'");
	}
	
	public void update() {
		String id = getPara("id");
		Manager manager= managerService.findById(id);
		setAttr("manager",manager);
		setAttr("date",CommonUtils.sdf.format(new Date()));
		render("update.html");
	}
	
	public void delete() {
		String id = getPara("id");
		managerService.deleteById(id);
//		redirect("/navigation");
		renderJavascript("window.location.href='/manager'");
	}
	
	public void saveRelation() {
		String managerIds= getPara("managerId");
		String roleIds = getPara("roleIds");
		managerRoleService.save(roleIds,managerIds);
		redirect("/role");
	}
	
	
	public void setRole() {
		String id = getPara("id");
		setAttr("managerId",id);
		render("setRole.html");
	}
}
