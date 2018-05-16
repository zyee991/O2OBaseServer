package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Firsttype;
import com.o2o.common.model.Sectype;

public class GoodsTypeService {
    
	private static final Firsttype dao=new Firsttype().dao();
	private static final Sectype secdao=new Sectype().dao();
	public Page<Firsttype> paginage(int pageNumber, int pageSize) {
		return dao.paginate(pageNumber, pageSize, "select *","from tb_first_type");
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
		  e.printStackTrace();
		  firsttype.update();
	  }
  }
public List<Sectype> findChildNavigationByParentId(String id) {
	List<Sectype> list = secdao.find("select*from tb_sec_type where first_type_id=?", id);
	return list;
}
public Sectype findChildById(String secid) {
	
	return secdao.findById(secid);
}
public void deleteFirstById(String firstid) {
	dao.deleteById(firstid);
	
}
public void deleteSecById(String secid) {
	secdao.deleteById(secid);
}
}
