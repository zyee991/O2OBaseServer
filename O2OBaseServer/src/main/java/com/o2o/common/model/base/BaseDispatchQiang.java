package com.o2o.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseDispatchQiang<M extends BaseDispatchQiang<M>> extends Model<M> implements IBean {

	public M setPacqId(java.lang.Integer pacqId) {
		set("pacq_id", pacqId);
		return (M)this;
	}
	
	public java.lang.Integer getPacqId() {
		return getInt("pacq_id");
	}

	public M setMid(java.lang.Integer mid) {
		set("mid", mid);
		return (M)this;
	}
	
	public java.lang.Integer getMid() {
		return getInt("mid");
	}

	public M setFatId(java.lang.Integer fatId) {
		set("fat_id", fatId);
		return (M)this;
	}
	
	public java.lang.Integer getFatId() {
		return getInt("fat_id");
	}

	public M setPacqStime(java.util.Date pacqStime) {
		set("pacq_stime", pacqStime);
		return (M)this;
	}
	
	public java.util.Date getPacqStime() {
		return get("pacq_stime");
	}

	public M setPacqEntime(java.util.Date pacqEntime) {
		set("pacq_entime", pacqEntime);
		return (M)this;
	}
	
	public java.util.Date getPacqEntime() {
		return get("pacq_entime");
	}

}
