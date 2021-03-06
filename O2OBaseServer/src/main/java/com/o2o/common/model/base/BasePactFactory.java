package com.o2o.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BasePactFactory<M extends BasePactFactory<M>> extends Model<M> implements IBean {

	public M setPfaId(java.lang.String pfaId) {
		set("pfa_id", pfaId);
		return (M)this;
	}
	
	public java.lang.String getPfaId() {
		return getStr("pfa_id");
	}

	public M setPid(java.lang.String pid) {
		set("pid", pid);
		return (M)this;
	}
	
	public java.lang.String getPid() {
		return getStr("pid");
	}

	public M setMid(java.lang.String mid) {
		set("mid", mid);
		return (M)this;
	}
	
	public java.lang.String getMid() {
		return getStr("mid");
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

	public M setPfaState(java.lang.Integer pfaState) {
		set("pfa_state", pfaState);
		return (M)this;
	}
	
	public java.lang.Integer getPfaState() {
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
