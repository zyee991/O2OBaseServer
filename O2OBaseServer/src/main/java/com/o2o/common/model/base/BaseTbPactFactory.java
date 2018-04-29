package com.o2o.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbPactFactory<M extends BaseTbPactFactory<M>> extends Model<M> implements IBean {

	public M setPfaId(java.lang.Integer pfaId) {
		set("pfa_id", pfaId);
		return (M)this;
	}
	
	public java.lang.Integer getPfaId() {
		return getInt("pfa_id");
	}

	public M setPid(java.lang.Integer pid) {
		set("pid", pid);
		return (M)this;
	}
	
	public java.lang.Integer getPid() {
		return getInt("pid");
	}

	public M setMid(java.lang.Integer mid) {
		set("mid", mid);
		return (M)this;
	}
	
	public java.lang.Integer getMid() {
		return getInt("mid");
	}

	public M setPfaStime(java.util.Date pfaStime) {
		set("pfa_stime", pfaStime);
		return (M)this;
	}
	
	public java.util.Date getPfaStime() {
		return get("pfa_stime");
	}

	public M setPfaEtime(java.util.Date pfaEtime) {
		set("pfa_etime", pfaEtime);
		return (M)this;
	}
	
	public java.util.Date getPfaEtime() {
		return get("pfa_etime");
	}

	public M setPfaState(java.lang.Boolean pfaState) {
		set("pfa_state", pfaState);
		return (M)this;
	}
	
	public java.lang.Boolean getPfaState() {
		return get("pfa_state");
	}

	public M setPfaIncome(java.lang.Float pfaIncome) {
		set("pfa_income", pfaIncome);
		return (M)this;
	}
	
	public java.lang.Float getPfaIncome() {
		return getFloat("pfa_income");
	}

}
