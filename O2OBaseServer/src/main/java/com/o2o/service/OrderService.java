package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.o2o.common.model.Order;

public class OrderService {

	private static final Order dao=new Order().dao();
	public Page<Record> paginate(int i, int j,String paystatus,String orderstatus,String anotherstatus) {
		String wheresql=" where a.user_openid=u.user_openid and t.sec_type_id=a.order_pay_type";
		if(paystatus!=""){
			wheresql+=" and order_pay_status="+paystatus;
		}
		if(orderstatus!=""){
			wheresql+=" and order_status="+orderstatus;
		}
		if(anotherstatus!=null){
			wheresql+=" and order_status="+anotherstatus;
		}
		Page<Record> page=Db.paginate(i, j,"select a.*,u.user_nickname,t.sec_type_name as pay_type_name","from tb_order a,tb_user u,tb_sec_type t"
				+wheresql);
		return page;
	}
	public List<Record> findGoodsByOrderId(String id) {
		List<Record>list=Db.find("select g.goodsinfo_name,y.sec_type_name,a.order_detail_num from tb_goodsinfo g,tb_sec_type y,tb_shop p,tb_shangjiaoperation s,tb_order_detail a"
				+ " where g.goodsinfo_id=s.goodsinfo_id and p.shop_id=s.shop_id and p.shop_id=a.shop_id and y.sec_type_id=g.sec_type_id and a.order_id=?",id);
		return list;
	}

}
