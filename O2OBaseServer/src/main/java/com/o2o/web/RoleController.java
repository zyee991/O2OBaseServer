package com.o2o.web;

import java.util.Date;
import java.util.UUID;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Role;
import com.o2o.service.RoleNavigationService;
import com.o2o.service.RoleService;

public class RoleController extends Controller {

	static RoleService roleService = new RoleService();
	static RoleNavigationService roleNavigationService = new RoleNavigationService();
	
	public void index(){
		setAttr("title","角色管理");
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
	
	public void saveRelation() {
		String roleId = getPara("roleId");
		String navigationIds = getPara("navigationIds");
		roleNavigationService.save(roleId,navigationIds);
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
	
	public void setRelation() {
		String id = getPara("id");
		setAttr("roleId",id);
		render("setRelation.html");
	}
}
