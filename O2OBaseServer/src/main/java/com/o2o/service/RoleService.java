package com.o2o.service;

import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Navigation;
import com.o2o.common.model.Role;

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
}
