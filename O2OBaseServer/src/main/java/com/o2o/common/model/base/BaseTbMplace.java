package com.o2o.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbMplace<M extends BaseTbMplace<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setPname(java.lang.String pname) {
		set("pname", pname);
		return (M)this;
	}
	
	public java.lang.String getPname() {
		return getStr("pname");
	}

	public M setPaddress(java.lang.String paddress) {
		set("paddress", paddress);
		return (M)this;
	}
	
	public java.lang.String getPaddress() {
		return getStr("paddress");
	}

	public M setPcontacts(java.lang.String pcontacts) {
		set("pcontacts", pcontacts);
		return (M)this;
	}
	
	public java.lang.String getPcontacts() {
		return getStr("pcontacts");
	}

	public M setPtime(java.lang.Integer ptime) {
		set("ptime", ptime);
		return (M)this;
	}
	
	public java.lang.Integer getPtime() {
		return getInt("ptime");
	}

	public M setPprice(java.lang.String pprice) {
		set("pprice", pprice);
		return (M)this;
	}
	
	public java.lang.String getPprice() {
		return getStr("pprice");
	}

}
