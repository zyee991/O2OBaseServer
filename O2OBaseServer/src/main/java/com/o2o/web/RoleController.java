package com.o2o.web;

import java.util.Date;
import java.util.UUID;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Navigation;
import com.o2o.common.model.Role;
import com.o2o.service.RoleService;
import com.o2o.validator.NavigationValidator;

public class RoleController extends Controller {

	static RoleService roleService = new RoleService();
	
	public void index(){
		setAttr("title","系统设置");
		Page<Role> page = roleService.paginate(1, 10);
		setAttr("page",page);
		render("index.html");
	}
	
	public void view() {
		String id = getPara("id");
		Role role= roleService.findById(id);
		setAttr("role",role);
		render("view.html");
	}
	
	public void add() {
		String id = getPara("id");
		Role role= roleService.findById(id);
		setAttr("role",role);
		setAttr("newId",UUID.randomUUID());
		setAttr("date",new Date());
		render("add.html");
	}
	

	public void save() {
		Role role = getBean(Role.class);
		roleService.save(role);
		redirect("/role");
	}
	
	
	public void update() {
		String id = getPara("id");
		Role role = roleService.findById(id);
		setAttr("role",role);
		setAttr("date",new Date());
		render("update.html");
	}
	
	public void delete() {
		String id = getPara("id");
		roleService.deleteById(id);
		redirect("/role");
	}
}
