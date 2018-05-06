package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.Dispatch;
import com.o2o.common.model.Muser;
import com.o2o.common.model.ServiceOrder;

public class ServiceOrderService {

	private static  final ServiceOrder dao=new ServiceOrder().dao();
	public List<Record> tableData() {
		List<Record>list=Db.find("select a.* from v_service_order a");
		return list;
	}

	public List<Record> reload(List<Record> list) {
		for(Record record:list){
			if(record.get("service_order_status").equals(0)&&record.get("service_pay_status").equals(1)){
				record.set("status_name", "待派单");
			}else if(record.get("service_order_status").equals(0)&&record.get("service_pay_status").equals(0)){
				record.set("status-name", "待支付");
			}else if(record.get("service_order_status").equals(1)){
				record.set("status_name", "待验收");
			}else if(record.get("service_pay_status").equals("2")){
				record.set("status_name", "待退款");
			}else if(record.get("service_pay_status").equals(3)&&record.get("service_order_status").equals(3)){
				record.set("status_name","退款完成");
			}else {
				record.set("status_name","已完成");
			}
		}
		return list;
	}

	public List<Record> finMuserList(String m_type) {
		
		return Db.find("select*from tb_muser where m_type=?",m_type);
	}

	public void save(Dispatch dispatch) {
		try{
			dispatch.save();
		}catch(Exception e){
			e.printStackTrace();
			dispatch.update();
		}
		
	}

	public ServiceOrder findOne(String orderId) {
		
		return dao.findById(orderId);
	}

}
 