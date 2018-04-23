package com.o2o.service;

import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Firsttype;
import com.o2o.common.model.Sectype;

public class GoodsTypeService {
    
	private static final Firsttype dao=new Firsttype().dao();
	public Page<Firsttype> paginage(int pageNumber, int pageSize) {
		return dao.paginate(pageNumber, pageSize, "select *","from tb_base_firsttype");
	}
	//查找一级类型
	public Firsttype findById(String id) {		
		return dao.findById(id);
	}
	public void saveSecondType(Sectype sectype) {
		try{
		sectype.save();
		}catch(Exception e){
			sectype.update();
		}
	}
  public void saveFirstType(Firsttype firsttype){
	  try{
		  firsttype.save();
	  }catch(Exception e){
		  firsttype.update();
	  }
  }
}
