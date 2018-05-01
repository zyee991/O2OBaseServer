package com.o2o.index;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;

import com.jfinal.core.Controller;
import com.jfinal.plugin.ehcache.CacheKit;
import com.o2o.common.model.Manager;
import com.o2o.service.ManagerService;
import com.o2o.util.BaseUtils;
import com.o2o.util.FtpUtil;
import com.o2o.util.SecurityAuthentication;
import com.o2o.web.NavigationController;

/**
 * 登录
 * @author z
 *
 */
public class IndexController extends Controller {
	
	// websocket服务器地址
	public static String hostname;
	// websocket端口
	public static Integer port;
	// endpoint
	public static String endpoint;


	static {
		Properties properties = new Properties();
		try {
			properties.load(FtpUtil.class.getClassLoader().getResourceAsStream("websocket.properties"));
			hostname = properties.getProperty("host");
			endpoint = properties.getProperty("endpoint");
			port = Integer.valueOf(properties.getProperty("port"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static ManagerService managerService = new ManagerService();
	
	@SuppressWarnings("rawtypes")
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
		setAttr("initWS",false);
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
			String cookieValue = SecurityAuthentication.encode("login", manager.getId());
			this.setCookie("o2oCookie", cookieValue, 3600);
			BaseUtils.putManager(manager,this);
			BaseUtils.putNavigation(NavigationController.getNavigationTree(manager), this);
			setAttr("initWS",true);
			String wsUrl = "ws://" + hostname + ":" + port + "/" + endpoint + "?id=" + manager.getId();
			setAttr("wsUrl",wsUrl);
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



