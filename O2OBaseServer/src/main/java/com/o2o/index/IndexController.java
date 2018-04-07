package com.o2o.index;

import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.o2o.common.model.Manager;
import com.o2o.util.BaseUtils;
import com.o2o.util.SecurityAuthentication;
import com.o2o.web.NavigationController;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * IndexController
 */
public class IndexController extends Controller {
	public void index() {
		String cookie = getCookie("o2oCookie");
		try {
			String id = SecurityAuthentication.decode("login", cookie);
			Manager manager = Manager.dao.findFirstByCache(BaseUtils.MANAGER_CACHE, id, "select * from tb_base_manager where id = ?",id);
			
			if(manager == null) {
				redirect("/toLogin");	
				return;
			} else {
				List<Map> map = NavigationController.getNavigationTree(manager);
				BaseUtils.putNavigation(map, this);
				setAttr("treelist",map);
			}
		} catch (Exception e) {
			redirect("/toLogin");		
			return;
		}
//		System.out.println("-----------------------------"+NavigationController.getNavigationTree().toString());
		render("index.html");
	}
	//登陆
	public void login() throws Exception{
		String name=getPara("name");
		String password=getPara("password");
		password = SecurityAuthentication.crypt(password);
		Manager manager=Manager.dao.findUserLogin(name,password);
		if(manager!=null){
			String cookieValue = SecurityAuthentication.encode("login", manager.getId());
			this.setCookie("o2oCookie", cookieValue, 3600);
			BaseUtils.putManager(manager,this);
			BaseUtils.putNavigation(NavigationController.getNavigationTree(manager), this);
			redirect("/index");
		}else{
			redirect("/toLogin");		
		}
	}
	
	public void toLogin() {
		render("login.html");
	}
}



