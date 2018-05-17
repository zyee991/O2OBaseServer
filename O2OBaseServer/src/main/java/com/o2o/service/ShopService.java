package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.Sectype;
import com.o2o.common.model.Shangjiaoperation;
import com.o2o.common.model.Shop;

public class ShopService {

	private static final Shop dao=new Shop().dao(); 
	private static final Shangjiaoperation shangjiadao=new Shangjiaoperation().dao();
	private static final Sectype typedao = new Sectype().dao();
	public Page<Record> paginate1(int i,int j){
		Page<Record> page=Db.paginate(i, j, "select* from view_shangpinon","");
		return page;
	}
	public List<Record> getGoodList(String typeid) {
		return Db.find("select * from tb_goodsinfo where typeid=?",typeid);
	}
	public void save(Shop shop, Shangjiaoperation shangjiaoperation) {
		try{
		shop.save();
		shangjiaoperation.save();
		}catch(Exception e){
			shop.update();
			shangjiaoperation.update();
		}
	}
	public List<Record> findById(String id) {
		List<Record> shoplist=Db.find("select a.*,b.goodsinfo_name from tb_shop a,tb_shangjiaoperation c,tb_goodsinfo b"
				+" where a.shop_id=c.shop_id and c.goodsinfo_id=b.goodsinfo_id and a.shop_id=?",id);
		return shoplist;
	}
	
	public void deleteById(String id, String shopgoodsid) {
		dao.deleteById(id);
		shangjiadao.deleteById(shopgoodsid);
	}
	public List<Sectype> getTypeList() {
		List<Sectype> typelist = typedao
				.find("select a.* from tb_sec_type a where a.first_type_id=1 and a.sec_type_id <> 0");
		/*
		 * Gson gson=new Gson(); String str=gson.toJson(typelist);
		 */
		return typelist;
	}
	
	public List<Record> findByTypeId(String typeId) {
		return Db.find("select* from tb_goodsinfo where sec_type_id=?",typeId);
	}
	
	public List<Record> findGoodById(String goodsid) {
		return Db.find("select * from tb_goodsinfo where goodsinfo_id=?",goodsid);
	}
	
	public List<Record> tableData() {
		List<Record>list=Db.find("select*from view_shop_on_all");
		return list;
	}
	

	
}
