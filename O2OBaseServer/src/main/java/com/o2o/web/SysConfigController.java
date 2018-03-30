package com.o2o.web;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Navigation;
import com.o2o.service.NavigationService;

public class SysConfigController extends Controller{
	
	static NavigationService navigationService = new NavigationService();
	
	public void index() {
		setAttr("title","系统设置");
		Page<Navigation> page = navigationService.paginate(1, 10);
		setAttr("page",page);
		render("sysConfig.html");
	}
}
