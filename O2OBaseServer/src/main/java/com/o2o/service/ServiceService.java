package com.o2o.service;

import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Service;

public class ServiceService {

	private static final Service dao=new Service().dao();
	public Page<Service> paginage(int pageNumber, int pageSize) {
	
		return dao.paginate(pageNumber,pageSize,"select*","from tb_base_service");
	}

}
