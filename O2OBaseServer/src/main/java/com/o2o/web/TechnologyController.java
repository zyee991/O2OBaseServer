package com.o2o.web;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.service.TechnologyService;

public class TechnologyController extends Controller {

	static TechnologyService service = new TechnologyService();

	public void index() {
		setAttr("title", "技术需求");
		render("index.html");
	}

	public void tableData() {
		List<Record> list = service.tableData();
		renderJson(list);
	}
	
	public void delete() {
		String id = getPara("id");
		service.deleteById(id);
		renderJavascript("window.location.href='/technology'");
	}
}
