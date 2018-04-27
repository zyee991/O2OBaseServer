package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.o2o.common.model.Order;

public class OrderService {

	private static final Order dao=new Order().dao();
	public List<Record> paginate(int i, int j) {
		Page<Record> page=Db.paginate(i, j,"select a.*,u.user_nickname,t.sec_type_name","from tb_base_sectype t,tb_base_order a,tb_base_user u"
				+" where a.user_openid=u.user_openid and t.sec_type_id=a.order_type");
		return page.getList();
	}

}
