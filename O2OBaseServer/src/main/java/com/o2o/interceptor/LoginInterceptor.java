package com.o2o.interceptor;

import java.io.IOException;
import java.util.Properties;

import com.jfinal.aop.Invocation;
import com.jfinal.aop.PrototypeInterceptor;
import com.jfinal.core.Controller;
import com.o2o.common.model.Manager;
import com.o2o.util.BaseUtils;
import com.o2o.util.FtpUtil;

public class LoginInterceptor extends PrototypeInterceptor {

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

	@Override
	public void doIntercept(Invocation inv) {
		Manager manager = BaseUtils.getManager(inv.getController());
		if (manager == null && !"/login".equals(inv.getActionKey())) {
			inv.getController().render("/WEB-INF/view/index/login.html");
		} else {
			Controller controller = inv.getController();
			controller.setAttr("treelist", BaseUtils.getNavigation(controller));
			controller.setAttr("showLogout", true);
			controller.setAttr("manager", manager);
			if (manager != null) {
				String wsUrl = new StringBuilder().append("ws://").append(hostname).append(":").append(port).append("/")
						.append(endpoint).append("?id=").append(manager.getId()).toString();
				controller.setAttr("wsUrl", wsUrl);
			}
			inv.invoke();
		}
	}

}
