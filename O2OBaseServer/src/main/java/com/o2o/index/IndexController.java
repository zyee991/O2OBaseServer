package com.o2o.index;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.core.Controller;
import com.o2o.common.model.Manager;
import com.o2o.util.SecurityAuthentication;

import net.sf.json.JSONObject;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * IndexController
 */
public class IndexController extends Controller {
	public void index() {
		render("index.html");
	}
	//登陆
	public void login(){
		String name=getPara("name");
		String password=getPara("password");
		password = SecurityAuthentication.crypt(password);
		Manager manager=Manager.dao.findUserLogin(name,password);
		if(manager!=null){
			this.setSessionAttr("managerlogin", manager);
			render("main.html");		
		}else{
			render("main.html");		

		}
	}
}



