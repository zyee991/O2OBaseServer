package com.o2o.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseRentFactory<M extends BaseRentFactory<M>> extends Model<M> implements IBean {

	public M setPid(java.lang.Integer pid) {
		set("pid", pid);
		return (M)this;
	}
	
	public java.lang.Integer getPid() {
		return getInt("pid");
	}

	public M setNameP(java.lang.String nameP) {
		set("name_p", nameP);
		return (M)this;
	}
	
	public java.lang.String getNameP() {
		return getStr("name_p");
	}

	public M setLenth(java.lang.Float lenth) {
		set("lenth", lenth);
		return (M)this;
	}
	
	public java.lang.Float getLenth() {
		return getFloat("lenth");
	}

	public M setWidth(java.lang.Float width) {
		set("width", width);
		return (M)this;
	}
	
	public java.lang.Float getWidth() {
		return getFloat("width");
	}

	public M setHeight(java.lang.Float height) {
		set("height", height);
		return (M)this;
	}
	
	public java.lang.Float getHeight() {
		return getFloat("height");
	}

	public M setOther(java.lang.String other) {
		set("other", other);
		return (M)this;
	}
	
	public java.lang.String getOther() {
		return getStr("other");
	}

	public M setStateT(java.lang.Boolean stateT) {
		set("state_t", stateT);
		return (M)this;
	}
	
	public java.lang.Boolean getStateT() {
		return get("state_t");
	}

	public M setAddressPid(java.lang.Integer addressPid) {
		set("address_pid", addressPid);
		return (M)this;
	}
	
	public java.lang.Integer getAddressPid() {
		return getInt("address_pid");
	}

}
