package com.o2o.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.Manager;
import com.o2o.common.model.Service;
import com.o2o.common.model.ServiceOrder;
import com.o2o.common.model.Technology;
import com.o2o.service.ServiceOrderService;
import com.o2o.service.ServiceService;
import com.o2o.service.TechnologyService;
import com.o2o.util.BaseUtils;

public class TechnologyController extends Controller {

	static TechnologyService service = new TechnologyService();
	static ServiceOrderService serviceOrderService = new ServiceOrderService();
	static ServiceService serviceService = new ServiceService();

	public void index() {
		setAttr("title", "技术需求");
		render("index.html");
	}

	public void tableData() {
		List<Record> list = service.tableData();
		renderJson(list);
	}
	
	public void delete() {
		String id = getPara("id");
		service.deleteById(id);
		renderJavascript("window.location.href='/technology'");
	}
	
	public void add() {
		String serviceOrderId = getPara("serviceOrderId");
		Manager manager = BaseUtils.getManager(this);
		Technology technology = new Technology();
		if(StringUtils.isNotBlank(serviceOrderId)) {
			ServiceOrder serviceOrder = serviceOrderService.findOne(serviceOrderId);
			if(serviceOrder != null) {
				String serviceId = serviceOrder.getServiceId();
				technology.setServiceOrderId(serviceOrderId);
				technology.setFatPrice(serviceOrder.getOrderPrice());
				Service service = serviceService.findById(serviceId);
				if(service != null) {
					technology.setFatName(service.getServiceName());
					technology.setDescrption(service.getServiceDesc());
				}
			}
		}
		technology.setFatId(UUID.randomUUID().toString());
		technology.setFatContacts(manager.getName());
		technology.setFatPhone(manager.getTelephone());
		technology.setFatState("0");
		technology.setFatSdate(new Date());
		setAttr("fmtDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		setAttr("technology",technology);
		render("add.html");
	}
	
	public void save() {
		Technology technology = getBean(Technology.class);
		if(StringUtils.isNotBlank(technology.getServiceOrderId())) {
			String serviceOrderId = technology.getServiceOrderId();
			ServiceOrder serviceOrder = serviceOrderService.findOne(serviceOrderId);
			serviceOrder.setServiceOrderStatus(1);
			serviceOrder.update();
		}
		service.save(technology);
		renderJavascript("window.location.href='/technology'");
	}
}
