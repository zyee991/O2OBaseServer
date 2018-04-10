package com.o2o.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.o2o.common.model.Navigation;

public class NavigationValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		validateRequiredString("navigation.name", "nameMsg", "请输入名称!");
		validateRequiredString("navigation.url", "urlMsg", "请输入URL!");
	}

	@Override
	protected void handleError(Controller c) {
		c.keepModel(Navigation.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/navigation/save"))
			controller.render("add.html");
		else if (actionKey.equals("/navigation/update"))
			controller.render("update.html");
	}

}
