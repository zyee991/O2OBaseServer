package com.o2o.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseServiceOrder<M extends BaseServiceOrder<M>> extends Model<M> implements IBean {

	public M setServiceOrderId(java.lang.Integer serviceOrderId) {
		set("service_order_id", serviceOrderId);
		return (M)this;
	}
	
	public java.lang.Integer getServiceOrderId() {
		return getInt("service_order_id");
	}

	public M setServiceId(java.lang.String serviceId) {
		set("service_id", serviceId);
		return (M)this;
	}
	
	public java.lang.String getServiceId() {
		return getStr("service_id");
	}

	public M setUserOpenid(java.lang.String userOpenid) {
		set("user_openid", userOpenid);
		return (M)this;
	}
	
	public java.lang.String getUserOpenid() {
		return getStr("user_openid");
	}

	public M setServiceCreateTime(java.util.Date serviceCreateTime) {
		set("service_create_time", serviceCreateTime);
		return (M)this;
	}
	
	public java.util.Date getServiceCreateTime() {
		return get("service_create_time");
	}

	public M setServicePayStatus(java.lang.Integer servicePayStatus) {
		set("service_pay_status", servicePayStatus);
		return (M)this;
	}
	
	public java.lang.Integer getServicePayStatus() {
		return getInt("service_pay_status");
	}

	public M setServiceIsforward(java.lang.Boolean serviceIsforward) {
		set("service_isforward", serviceIsforward);
		return (M)this;
	}
	
	public java.lang.Boolean getServiceIsforward() {
		return get("service_isforward");
	}

	public M setServiceOrderStatus(java.lang.Integer serviceOrderStatus) {
		set("service_order_status", serviceOrderStatus);
		return (M)this;
	}
	
	public java.lang.Integer getServiceOrderStatus() {
		return getInt("service_order_status");
	}

}
