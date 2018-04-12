package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Manager;
import com.o2o.common.model.Navigation;
import com.o2o.common.model.Role;
import com.o2o.util.BaseUtils;

public class RoleService {
private static final Role dao = new Role().dao();
	
	public Page<Role> paginate(int pageNumber,int pageSize) {
		return dao.paginate(pageNumber, pageSize, "select *","from tb_base_role");
	}
	
	public Role findById(String id) {
		return dao.findById(id);
	}
	
	public void deleteById(String id) {
		dao.deleteById(id);
	}
	
	public void save(Role role) {
		try {
			role.save();	
		} catch(Exception e) {
			role.update();	
		}
	}

	public List<Role> findRoleByManager(Manager manager) {
		if(BaseUtils.isSuperUser(manager)) {
			return findParentRole();
		} else {
			String sqlPara = "select n.* from tb_base_role as n where n.id = ("
					+ "select rn.roleId from tb_base_manager_role as rn where rn.managerId = ("
					+ "select mr.managerId from tb_base_manager_role as mr where mr.managerId = ?))";
			List<Role> list = dao.find(sqlPara,manager.getId());
			return list;
		}
	}
	
	public List<Role> findParentRole(){
		List<Role> list=dao.find("select*from tb_base_role");
		return list;
	}
	
}
