package com.o2o.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.PactEmploy;
import com.o2o.service.RecruitService;

public class RecruitController extends Controller {

	RecruitService recruitService = new RecruitService();

	public void index() {
		setAttr("title", "应聘者列表");
		setAttr("id", getPara("id"));
		render("index.html");
	}

	public void tableData() {
		String id = getPara("id");
		List<Record> list = recruitService.tableData(id);
		renderJson(recruitService.reload(list));
	}

	public void confirmOK() {
		String status = getPara("status");
		String pemid = getPara("pemid");
		String rid = getPara("rid");
		Date date = new Date();
		PactEmploy pactemploy = PactEmploy.dao.findById(pemid);
		pactemploy.setPeState(Integer.parseInt(status));
		pactemploy.setPeEtime(date);
		pactemploy.update();
		Map<String,String> resultMap = new HashMap<>();
		resultMap.put("url", "/recruit?id"+rid);
		renderJson(resultMap);
	}
}
