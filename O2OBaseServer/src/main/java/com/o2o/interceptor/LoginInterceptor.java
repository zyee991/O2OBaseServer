package com.o2o.interceptor;

import com.jfinal.aop.Invocation;
import com.jfinal.aop.PrototypeInterceptor;
import com.o2o.common.model.Manager;
import com.o2o.util.BaseUtils;

public class LoginInterceptor extends PrototypeInterceptor{

	@Override
	public void doIntercept(Invocation inv) {
		Manager manager = BaseUtils.getManager(inv.getController());
		if(manager == null && !"/login".equals(inv.getActionKey())) {
			inv.getController().render("/WEB-INF/view/index/login.html");
		} else {
			inv.invoke();	
		}
	}

}