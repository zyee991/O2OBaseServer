package com.o2o.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.PactFactory;
import com.o2o.common.model.RentFactory;
import com.o2o.service.FactoryPactService;

public class FactoryPactController extends Controller {

	FactoryPactService factoryPactService = new FactoryPactService();

	public void index() {
		String id = getPara("id");
		setAttr("id", id);
		setAttr("title", "场地租赁记录列表");
		render("index.html");
	}

	public void tableData() {
		String id = getPara("id");
		List<Record> list = factoryPactService.tableData(id);
		renderJson(factoryPactService.reload(list));
	}

	public void confirmOK() {
		Map<String, String> resultMap = new HashMap<>();
		String status = getPara("status");
		String pfaid = getPara("pfaid");
		String pid = getPara("pid");
		// 场地的状态变成不可租
		RentFactory rentFactory = RentFactory.dao.findById(pid);
		rentFactory.setState(true);
		rentFactory.update();
		Date date = new Date();
		PactFactory pactfactory = PactFactory.dao.findById(pfaid);
		pactfactory.setPfaState(Integer.parseInt(status));
		pactfactory.setPfaEtime(date);
		pactfactory.update();
		resultMap.put("content", "审批通过");
		renderJson(resultMap);
		renderJavascript("window.location.href='/recruit?pid='" + pid);
	}
}
