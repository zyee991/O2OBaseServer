package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.Order;

public class OrderService {

	private static final Order dao = new Order().dao();

	public Page<Record> paginate(int i, int j, String paystatus, String orderstatus, String anotherstatus) {
		String wheresql = " where a.user_openid=u.user_openid and t.sec_type_id=a.order_pay_type";
		if (paystatus != "") {
			wheresql += " and order_pay_status=" + paystatus;
		}
		if (orderstatus != "") {
			wheresql += " and order_status=" + orderstatus;
		}
		if (anotherstatus != null) {
			wheresql += " and order_status=" + anotherstatus;
		}
		Page<Record> page = Db.paginate(i, j, "select a.*,u.user_nickname,t.sec_type_name as pay_type_name",
				"from tb_order a,tb_user u,tb_sec_type t" + wheresql);
		return page;
	}
	
	public List<Record> tableData() {
		String wheresql = " where a.user_openid=u.user_openid and t.sec_type_id=a.order_pay_type";
		List<Record> list = Db.find("select a.*,u.user_nickname,t.sec_type_name as pay_type_name " +
				"from tb_order a,tb_user u,tb_sec_type t" + wheresql);
		return list;
	}

	public Page<Record> paginateAll(int i, int j) {
		String wheresql = " where a.user_openid=u.user_openid and t.sec_type_id=a.order_pay_type";
		Page<Record> page = Db.paginate(i, j, "select a.*,u.user_nickname,t.sec_type_name as pay_type_name",
				"from tb_order a,tb_user u,tb_sec_type t" + wheresql);
		return page;
	}
	
	public List<Record> findGoodsByOrderId(String id) {
		List<Record> list = Db.find(
				"select g.goodsinfo_name,y.sec_type_name,a.order_detail_num from tb_goodsinfo g,tb_sec_type y,tb_shop p,tb_shangjiaoperation s,tb_order_detail a"
						+ " where g.goodsinfo_id=s.goodsinfo_id and p.shop_id=s.shop_id and p.shop_id=a.shop_id and y.sec_type_id=g.sec_type_id and a.order_id=?",
				id);
		return list;
	}
	
	public List<Record> reload(List<Record> list) {
		for(Record record:list) {
			if(record.get("order_status").equals(0) && record.get("order_pay_status").equals(1)) {
				record.set("status_name", "待发货");
			} else if (record.get("order_status").equals(1)) {
				record.set("status_name", "待收货");
			} else if (record.get("order_pay_status").equals(2)) {
				record.set("status_name", "待退款");
			} else if(record.get("order_status").equals(0)&&record.get("order_pay_status").equals(3)){
				record.set("status_name", "退款成功");
			}else if(record.get("order_pay_status").equals(0)&&record.get("order_status").equals(0)){
				record.set("status_name", "待支付");
			} else{
				record.set("status_name", "已完成");
			}
		}
		
		return list;
	}

	public Order findOne(String orderId) {
		return dao.findById(orderId);
	}

	public List<Record> findOrderByOrderId(String id) {
		String wheresql = " where a.user_openid=u.user_openid and t.sec_type_id=a.order_pay_type and a.district_id=d.district_id  and a.order_id='"+id+"'";
		List<Record>list=Db.find("select a.*,u.user_nickname,t.sec_type_name as pay_type_name,d.* from tb_order a,tb_user u,tb_sec_type t,view_address d"+wheresql);
		return list;
	}

	public List<Record> findDetailList(String orderId) {
	     String wheresql=" where a.shop_id=s.shop_id and order_id='"+orderId+"'";
	     List<Record>list=Db.find("select a.*,s.* from tb_order_detail a,tb_shop s");
		return list;
	}
} 
