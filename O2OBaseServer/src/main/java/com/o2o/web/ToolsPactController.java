package com.o2o.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.PactRent;
import com.o2o.common.model.RentTools;
import com.o2o.service.ToolPactService;

public class ToolsPactController extends Controller {

	private static ToolPactService toolPactService = new ToolPactService();

	public void index() {
		String tid = getPara("id");
		setAttr("title", "工具租赁记录");
		setAttr("id", tid);
		render("index.html");
	}

	public void tableData() {
		String id = getPara("id");
		List<Record> list = toolPactService.tableData(id);
		renderJson(toolPactService.reload(list));
	}

	public void confirmOK() {
		Map<String, String> resultMap = new HashMap<String, String>();
		String status = getPara("status");
		String pfeid = getPara("pfeid");
		String tid = getPara("tid");
		// 工具的状态变为不可租
		RentTools renttool = RentTools.dao.findById(tid);
		renttool.setState(1);
		renttool.update();
		Date date = new Date();
		PactRent pacttools = PactRent.dao.findById(pfeid);
		pacttools.setPreState(Integer.parseInt(status));
		pacttools.setPreEtime(date);
		pacttools.update();
		resultMap.put("content", "审批通过");
		resultMap.put("url", "/pacttools?id="+tid);
		renderJson(resultMap);
	}
}
