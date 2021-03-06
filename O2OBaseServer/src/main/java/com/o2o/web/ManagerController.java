package com.o2o.web;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.Manager;
import com.o2o.service.ManagerRoleService;
import com.o2o.service.ManagerService;
import com.o2o.util.CommonUtils;
import com.o2o.util.SecurityAuthentication;

public class ManagerController extends Controller {

	static ManagerService managerService = new ManagerService();
	static ManagerRoleService managerRoleService = new ManagerRoleService();

	public void index() {
		setAttr("title", "用户管理");
		render("index.html");
	}

	public void tableData() {
		List<Record> list = managerService.tableData();
		renderJson(list);
	}

	public void view() {
		String id = getPara("id");
		Manager manager = managerService.findById(id);
		setAttr("manager", manager);
		render("view.html");
	}

	public void add() {
		String id = getPara("id");
		if (id != null) {
			Manager manager = managerService.findById(id);
			setAttr("manager", manager);
		}
		setAttr("newId", UUID.randomUUID());
		setAttr("date", CommonUtils.sdf.format(new Date()));
		render("add.html");
	}

	public void save() {
		Manager manager = getBean(Manager.class);
		String password = SecurityAuthentication.crypt(manager.getPassword());
		manager.setPassword(password);
		managerService.save(manager);
		// redirect("/manager");
		renderJavascript("window.location.href='/manager'");
	}

	public void update() {
		String id = getPara("id");
		Manager manager = managerService.findById(id);
		setAttr("manager", manager);
		setAttr("date", CommonUtils.sdf.format(new Date()));
		render("update.html");
	}

	public void delete() {
		String id = getPara("id");
		managerService.deleteById(id);
		// redirect("/navigation");
		renderJavascript("window.location.href='/manager'");
	}

	public void saveRelation() {
		String managerId = getPara("managerId");
		String roleIds = getPara("roleIds");
		managerRoleService.save(managerId, roleIds);
		redirect("/role");
	}

	public void setRole() {
		String id = getPara("id");
		setAttr("managerId", id);
		render("setRole.html");
	}
}
