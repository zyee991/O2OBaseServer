package com.o2o.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.core.Controller;
import com.jfinal.plugin.ehcache.CacheKit;
import com.o2o.common.model.Manager;

public class BaseUtils {
	public static String MANAGER_CACHE = "ManagerCache";
	public static String NAVIGATION_CACHE = "NavigationCache";
	
	public static void putManager(Manager manager,Controller controller) {
		String cookie = controller.getCookie("o2oCookie");
		try {
//			String id = SecurityAuthentication.decode("login", cookie);
			CacheKit.put(MANAGER_CACHE, cookie, manager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Manager getManager(Controller controller) {
		String cookie = controller.getCookie("o2oCookie");
		try {
//			String id = SecurityAuthentication.decode("login", cookie);
			Manager manager = CacheKit.get(MANAGER_CACHE, cookie);
			if(manager == null) {
				manager = Manager.dao.findById(cookie);
			}	
			return manager;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void putNavigation(@SuppressWarnings("rawtypes") List<Map> map,Controller controller) {
		Manager manager = getManager(controller);
		if(manager != null) {
			CacheKit.put(NAVIGATION_CACHE, getManager(controller).getId(), map);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static List<Map> getNavigation(Controller controller) {
		Manager manager = getManager(controller);
		if(manager != null) {
			return CacheKit.get(NAVIGATION_CACHE, getManager(controller).getId());
		} else {
			return null;
		}
	}
	
	public static boolean isSuperUser(Manager manager) {
		return manager.getId().equals(Manager.SUPERUSER);
	}
	
	public static String getIpAddr(HttpServletRequest request) {  
        String ip = request.getHeader("x-forwarded-for");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        if (ip.equals("0:0:0:0:0:0:0:1")) {  
            ip = "本地";  
        }  
        return ip;  
    } 
}
