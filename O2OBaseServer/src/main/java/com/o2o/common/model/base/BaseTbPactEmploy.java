package com.o2o.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbPactEmploy<M extends BaseTbPactEmploy<M>> extends Model<M> implements IBean {

	public M setPemId(java.lang.Integer pemId) {
		set("pem_id", pemId);
		return (M)this;
	}
	
	public java.lang.Integer getPemId() {
		return getInt("pem_id");
	}

	public M setRid(java.lang.Integer rid) {
		set("rid", rid);
		return (M)this;
	}
	
	public java.lang.Integer getRid() {
		return getInt("rid");
	}

	public M setMid(java.lang.Integer mid) {
		set("mid", mid);
		return (M)this;
	}
	
	public java.lang.Integer getMid() {
		return getInt("mid");
	}

	public M setPeStime(java.util.Date peStime) {
		set("pe_stime", peStime);
		return (M)this;
	}
	
	public java.util.Date getPeStime() {
		return get("pe_stime");
	}

	public M setPeEtime(java.util.Date peEtime) {
		set("pe_etime", peEtime);
		return (M)this;
	}
	
	public java.util.Date getPeEtime() {
		return get("pe_etime");
	}

	public M setPeState(java.lang.Boolean peState) {
		set("pe_state", peState);
		return (M)this;
	}
	
	public java.lang.Boolean getPeState() {
		return get("pe_state");
	}

	public M setPreIncome(java.lang.Float preIncome) {
		set("pre_income", preIncome);
		return (M)this;
	}
	
	public java.lang.Float getPreIncome() {
		return getFloat("pre_income");
	}

}
