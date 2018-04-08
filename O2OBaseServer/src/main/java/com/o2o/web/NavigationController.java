package com.o2o.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Manager;
import com.o2o.common.model.Navigation;
import com.o2o.service.NavigationService;
import com.o2o.validator.NavigationValidator;

public class NavigationController extends Controller{
	
	static NavigationService navigationService = new NavigationService();
	
	public void index() {
		setAttr("title","导航设置");
		Page<Navigation> page = navigationService.paginate(1, 10);
		setAttr("page",page);
		render("index.html");
	}
	
	public void view() {
		String id = getPara("id");
		Navigation navigation = navigationService.findById(id);
		setAttr("navigation",navigation);
		render("view.html");
	}
	
	public void add() {
		String id = getPara("id");
		Navigation navigation = navigationService.findById(id);
		setAttr("navigation",navigation);
		setAttr("newId",UUID.randomUUID());
		setAttr("date",new Date());
		render("add.html");
	}
	
	@Before(NavigationValidator.class)
	public void save() {
		Navigation navigation = getBean(Navigation.class);
		navigationService.save(navigation);
		redirect("/navigation");
	}
	
	public void showSub(){
		String id = getPara("id");
		List<Navigation> list = navigationService.findChildNavigationByParentId(id);
		renderJson(list);
	}
	
	@Before(NavigationValidator.class)
	public void update() {
		String id = getPara("id");
		Navigation navigation = navigationService.findById(id);
		setAttr("navigation",navigation);
		setAttr("date",new Date());
		render("update.html");
	}
	
	public void delete() {
		String id = getPara("id");
		navigationService.deleteById(id);
		redirect("/navigation");
	}
	
	@SuppressWarnings("rawtypes")
	public static List<Map> getNavigationTree(Manager manager){
		List<Map> treelist=new ArrayList<Map>();
		List<Navigation> parentlist=navigationService.findParentNavigationByManager(manager);
		for(Navigation oneparentmap:parentlist){
			Map<String,Object> ParentMap=new HashMap<String,Object>();
			List<Navigation> childlist=navigationService.findChildNavigationByParentIdAndManager(manager,oneparentmap.getId());
			System.out.println(oneparentmap.getId());
			ParentMap.put("id",oneparentmap.getId().toString());
			ParentMap.put("name", oneparentmap.getName());
			ParentMap.put("url", oneparentmap.getUrl());
			ParentMap.put("chilid",childlist);
			treelist.add(ParentMap);
		}
		/*JSONArray jsonarray=JSONArray.fromObject(treelist);
		json.put("navigationtree", jsonarray);*/
		return treelist;
	}
}
