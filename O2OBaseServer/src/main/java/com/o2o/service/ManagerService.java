package com.o2o.service;

import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Manager;
import com.o2o.common.model.Navigation;

public class ManagerService {

	private static final Manager dao = new Manager().dao();
	public Page<Manager> paginate(int pageNumber,int pageSize) {
		return dao.paginate(pageNumber, pageSize, "select *","from tb_base_manager");
	}

	public Manager findById(String id) {
		
		return dao.findById(id);
	}

	public void deleteById(String id) {
		
		dao.deleteById(id);
		
	}

	public void save(Manager manager) {
		
		try {
			manager.save();	
		} catch(Exception e) {
			manager.update();	
		}
	}

}
