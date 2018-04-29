package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Sectype;
import com.o2o.common.model.Service;

public class ServiceService {

	private static final Service dao=new Service().dao();
	private static final Sectype typedao=new Sectype().dao();
	public Page<Service> paginage(int pageNumber, int pageSize) {
	
		return dao.paginate(pageNumber,pageSize,"select*","from tb_service");
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

	public List<Sectype> getTypeList() {
		List<Sectype> typelist=typedao.find("select a.* from tb_sectype a where a.first_type_id=2");
		return typelist;
	}
}
