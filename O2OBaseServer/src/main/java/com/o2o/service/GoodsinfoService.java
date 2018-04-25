package com.o2o.service;

import java.util.List;

import com.google.gson.Gson;
import com.jfinal.plugin.activerecord.Page;
import com.o2o.common.model.Goodsinfo;
import com.o2o.common.model.Sectype;

public class GoodsinfoService {

	private static final Goodsinfo dao=new Goodsinfo().dao();
	private static final Sectype typedao=new Sectype().dao();
	// 查询商品
	public  Page<Goodsinfo> paginate(int pageNumber,int pageSize){
		return dao.paginate(pageNumber, pageSize, "select*","from tb_base_goodsinfo where goodsinfo_is_taocan = 0");
	}
	
	// 查询套餐
	public  Page<Goodsinfo> paginateTaocan(int pageNumber,int pageSize){
		return dao.paginate(pageNumber, pageSize, "select*","from tb_base_goodsinfo where goodsinfo_is_taocan = 1");
	}
	
	public Goodsinfo findById(String id) {
		return dao.findById(id);
	}
	
	public void deleteById(String id) {
		dao.deleteById(id);
	}
	
public void save(Goodsinfo goodsinfo) {
		
		try {
			goodsinfo.save();	
		} catch(Exception e) {
			goodsinfo.update();	
		}
}

   public List<Sectype> getTypeList(){
	   List<Sectype> typelist= typedao.find("select a.* from tb_base_sectype a where a.first_type_id=1");
		/*Gson gson=new Gson();
		String str=gson.toJson(typelist);*/
	     return typelist;
   }
}
