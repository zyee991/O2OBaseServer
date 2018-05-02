package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.o2o.common.model.Order;

public class OrderService {

	private static final Order dao=new Order().dao();
	public List<Record> paginate(int i, int j,String paystatus,String orderstatus,String anotherstatus) {
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
		return page.getList();
	}

}
