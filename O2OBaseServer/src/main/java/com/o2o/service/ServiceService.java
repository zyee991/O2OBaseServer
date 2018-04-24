package com.o2o.service;

import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Service;

public class ServiceService {

	private static final Service dao=new Service().dao();
	public Page<Service> paginage(int pageNumber, int pageSize) {
	
		return dao.paginate(pageNumber,pageSize,"select*","from tb_base_service");
	}

	public void save(Service service){
		try{
			service.save();
		}catch(Exception e){
			service.update();
		}	
	}

	public Service findById(String id) {
	 return dao.findById(id);
	}

	public void deleteById(String id) {
		dao.deleteById(id);
	}
}
