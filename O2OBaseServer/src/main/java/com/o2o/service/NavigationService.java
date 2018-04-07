package com.o2o.service;

import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Navigation;

public class NavigationService {
	private static final Navigation dao = new Navigation().dao();
	
	public Page<Navigation> paginate(int pageNumber,int pageSize) {
		return dao.paginate(pageNumber, pageSize, "select *","from tb_base_navigation where parentId is null");
	}
	
	public Navigation findById(String id) {
		return dao.findById(id);
	}
	
	public void deleteById(String id) {
		dao.deleteById(id);
	}
	
	public void save(Navigation navigation) {
		try {
			navigation.save();	
		} catch(Exception e) {
			navigation.update();	
		}
	}

	public List<Navigation> findChildNavigationByParentId(String id) {
		List<Navigation> list = dao.find("select * from tb_base_navigation where parentId=?",id);
		return list;
	}
	
	public List<Navigation> findParentNavigation(){
		List<Navigation> list=dao.find("select*from tb_base_navigation where parentId is null");
		return list;
	}
}
