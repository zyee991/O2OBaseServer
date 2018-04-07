package com.o2o.interceptor;

import com.jfinal.aop.Invocation;
import com.jfinal.aop.PrototypeInterceptor;
import com.jfinal.core.Controller;
import com.o2o.util.BaseUtils;

public class NavigationInterceptor extends PrototypeInterceptor {

	@Override
	public void doIntercept(Invocation inv) {
		Controller controller = inv.getController();
		controller.setAttr("treelist", BaseUtils.getNavigation(controller));
		inv.invoke();
	}

}
