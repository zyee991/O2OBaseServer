package com.o2o.service;

import java.util.List;
import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Manager;
import com.o2o.common.model.Navigation;
import com.o2o.util.BaseUtils;

public class NavigationService {
	private static final Navigation dao = new Navigation().dao();
	
	public Page<Navigation> paginate(int pageNumber,int pageSize) {
		return dao.paginate(pageNumber, pageSize, "select *","from tb_base_navigation where parentId is null order by sortNumber");
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
		List<Navigation> list = dao.find("select * from tb_base_navigation where parentId=? order by sortNumber",id);
		return list;
	}
	
	public List<Navigation> findParentNavigation(){
		List<Navigation> list=dao.find("select*from tb_base_navigation where parentId is null order by sortNumber");
		return list;
	}
	
	public List<Navigation> findNavigationByRoleId(String roleId) {
		String sqlPara = "select n.* from tb_base_navigation as n where n.id = ("
				+ "select rn.navigationId from tb_base_role_navigation as rn where "
				+ "rn.roleId = ? ) order by n.sortNumber";
		return dao.find(sqlPara,roleId);
	}
	
	public List<Navigation> findParentNavigationByManager(Manager manager) {
		if(BaseUtils.isSuperUser(manager)) {
			return findParentNavigation();
		} else {
			String sqlPara = "select n.* from tb_base_navigation as n where n.id in ("
					+ "select rn.navigationId from tb_base_role_navigation as rn where rn.roleId = ("
					+ "select mr.roleId from tb_base_manager_role as mr where mr.managerId = ?))"
					+ "and n.parentId is null order by n.sortNumber";
			List<Navigation> list = dao.find(sqlPara,manager.getId());
			return list;
		}
	}
	
	public List<Navigation> findChildNavigationByParentIdAndManager(Manager manager,String id) {
		if(BaseUtils.isSuperUser(manager)) {
			return findChildNavigationByParentId(id);
		} else { 
			String sqlPara = "select n.* from tb_base_navigation as n where n.id in ("
					+ "select rn.navigationId from tb_base_role_navigation as rn where rn.roleId in ("
					+ "select mr.roleId from tb_base_manager_role as mr where mr.managerId = ?))"
					+ "and n.parentId = ? order by n.sortNumber";
			List<Navigation> list = dao.find(sqlPara,manager.getId(),id);
			return list;
		}
	}
}
