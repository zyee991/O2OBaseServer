package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.Goodsinfo;
import com.o2o.common.model.Shangjiaoperation;
import com.o2o.common.model.Shop;

public class ShopService {

	private static final Shop dao=new Shop().dao(); 
	private static final Shangjiaoperation shangjiadao=new Shangjiaoperation().dao();
	public Page<Record> paginate1(int i,int j){
		Page<Record> page=Db.paginate(i, j, "select a.*,c.*,b.goodsinfo_name","from tb_shop a,tb_shangjiaoperation c,tb_goodsinfo b"
				+" where a.shop_id=c.shop_id and c.goodsinfo_id=b.goodsinfo_id");
		return page;
	}
	public List<Record> getGoodList() {
		return Db.find("select * from tb_goodsinfo");
	}
	public void save(Shop shop, Shangjiaoperation shangjiaoperation) {
		System.out.println(shop);
		shop.save();
		shangjiaoperation.save();
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
	
}
