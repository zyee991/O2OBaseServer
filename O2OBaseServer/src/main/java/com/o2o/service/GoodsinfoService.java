package com.o2o.service;

import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Goodsinfo;
import com.o2o.common.model.Manager;

public class GoodsinfoService {

	private static final Goodsinfo dao=new Goodsinfo().dao();
	public  Page<Goodsinfo> paginate(int pageNumber,int pageSize){
		return dao.paginate(pageNumber, pageSize, "select*","from tb_base_goodsinfo");
	}
	public Goodsinfo findById(String id) {
		
		return null;
	}
	
	public Goodsinfo deleteById(String id) {
		
		return null;
	}
	
public void save(Goodsinfo goodsinfo) {
		
		try {
			goodsinfo.save();	
		} catch(Exception e) {
			e.printStackTrace();
			goodsinfo.update();	
		}
}
}
