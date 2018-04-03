package com.o2o.index;

import com.jfinal.core.Controller;
import com.o2o.common.model.Manager;
import com.o2o.util.SecurityAuthentication;

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
			String userInfo = SecurityAuthentication.decode("login", cookie);
			Manager manager = Manager.dao.findUserLogin(userInfo.split("@")[0], userInfo.split("@")[1]);
			if(manager == null) {
				redirect("/toLogin");	
				return;
			}
		} catch (Exception e) {
			redirect("/toLogin");		
			return;
		}
		render("index.html");
	}
	//登陆
	public void login() throws Exception{
		String name=getPara("name");
		String password=getPara("password");
		password = SecurityAuthentication.crypt(password);
		System.out.println(password);
		Manager manager=Manager.dao.findUserLogin(name,password);
		if(manager!=null){
			String cookieValue = SecurityAuthentication.encode("login", name + "@" + password);
			this.setCookie("o2oCookie", cookieValue, 3600);
			redirect("/index");
		}else{
			redirect("/toLogin");		
		}
	}
	
	public void toLogin() {
		render("login.html");
	}
}



