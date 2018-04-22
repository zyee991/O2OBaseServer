package com.o2o.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseOrder<M extends BaseOrder<M>> extends Model<M> implements IBean {

	public M setOrderId(java.lang.Integer orderId) {
		set("order_id", orderId);
		return (M)this;
	}
	
	public java.lang.Integer getOrderId() {
		return getInt("order_id");
	}

	public M setFeedbackId(java.lang.Integer feedbackId) {
		set("feedback_id", feedbackId);
		return (M)this;
	}
	
	public java.lang.Integer getFeedbackId() {
		return getInt("feedback_id");
	}

	public M setUserOpenid(java.lang.String userOpenid) {
		set("user_openid", userOpenid);
		return (M)this;
	}
	
	public java.lang.String getUserOpenid() {
		return getStr("user_openid");
	}

	public M setOrderCreatetime(java.util.Date orderCreatetime) {
		set("order_createtime", orderCreatetime);
		return (M)this;
	}
	
	public java.util.Date getOrderCreatetime() {
		return get("order_createtime");
	}

	public M setOrderType(java.lang.String orderType) {
		set("order_type", orderType);
		return (M)this;
	}
	
	public java.lang.String getOrderType() {
		return getStr("order_type");
	}

	public M setOrderPayStatus(java.lang.Integer orderPayStatus) {
		set("order_pay_status", orderPayStatus);
		return (M)this;
	}
	
	public java.lang.Integer getOrderPayStatus() {
		return getInt("order_pay_status");
	}

	public M setOrderIsforward(java.lang.Boolean orderIsforward) {
		set("order_isforward", orderIsforward);
		return (M)this;
	}
	
	public java.lang.Boolean getOrderIsforward() {
		return get("order_isforward");
	}

	public M setOrderStatus(java.lang.Integer orderStatus) {
		set("order_status", orderStatus);
		return (M)this;
	}
	
	public java.lang.Integer getOrderStatus() {
		return getInt("order_status");
	}

}
