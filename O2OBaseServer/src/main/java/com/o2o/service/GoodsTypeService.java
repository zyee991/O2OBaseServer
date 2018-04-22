package com.o2o.service;

import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Firsttype;

public class GoodsTypeService {
    
	private static final Firsttype dao=new Firsttype().dao();
	public Page<Firsttype> paginage(int pageNumber, int pageSize) {
		return dao.paginate(pageNumber, pageSize, "select *","from tb_base_firsttype");
	}

}
