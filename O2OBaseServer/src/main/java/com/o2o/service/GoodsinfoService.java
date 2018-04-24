package com.o2o.service;

import java.util.List;

import com.google.gson.Gson;
import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Goodsinfo;
import com.o2o.common.model.Sectype;

public class GoodsinfoService {

	private static final Goodsinfo dao=new Goodsinfo().dao();
	private static final Sectype typedao=new Sectype().dao();
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

   public List<Sectype> getTypeList(){
	   List<Sectype> typelist= typedao.find("select a.sec_type_name from tb_base_sectype a where a.first_type_id=1");
		/*Gson gson=new Gson();
		String str=gson.toJson(typelist);*/
	     return typelist;
   }
}