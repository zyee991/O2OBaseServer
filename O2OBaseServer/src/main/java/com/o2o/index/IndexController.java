package com.o2o.index;

import java.util.Date;
import com.jfinal.core.Controller;
import com.jfinal.plugin.ehcache.CacheKit;
import com.o2o.common.model.Manager;
import com.o2o.service.ManagerService;
import com.o2o.util.BaseUtils;
import com.o2o.util.SecurityAuthentication;
import com.o2o.web.NavigationController;

/**
 * 登录
 * @author z
 *
 */
public class IndexController extends Controller {
	static ManagerService managerService = new ManagerService();
	
	public void index() {
		render("index.html");
	}
	
	//登陆
	public void login() throws Exception{
		String name=getPara("name");
		String password=getPara("password");
		password = SecurityAuthentication.crypt(password);
		Manager manager=Manager.dao.findUserLogin(name,password);
		if(manager!=null){
			String ip = BaseUtils.getIpAddr(this.getRequest());
			Date date = new Date();
			manager.setLoginIp(ip);
			manager.setLoginDate(date);
			managerService.save(manager);
//			String cookieValue = SecurityAuthentication.encode("login", manager.getId());
			String cookieValue =  manager.getId();
			this.setCookie("o2oCookie", cookieValue, 3600);
			BaseUtils.putManager(manager,this,manager.getId());
			BaseUtils.putNavigation(NavigationController.getNavigationTree(manager), this,manager.getId());
			redirect("/index");
		}else{
			redirect("/toLogin");
		}
	}
	
	public void toLogin() {
		render("login.html");
	}
	
	public void logout() {
		Manager manager = BaseUtils.getManager(this);
		removeCookie("o2oCookie");
		CacheKit.remove(BaseUtils.MANAGER_CACHE, manager.getId());
		redirect("toLogin");
	}
}



