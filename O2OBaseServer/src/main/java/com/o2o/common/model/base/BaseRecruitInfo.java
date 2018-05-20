package com.o2o.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseRecruitInfo<M extends BaseRecruitInfo<M>> extends Model<M> implements IBean {

	public M setRid(java.lang.String rid) {
		set("rid", rid);
		return (M)this;
	}
	
	public java.lang.String getRid() {
		return getStr("rid");
	}

	public M setExperice(java.lang.String experice) {
		set("experice", experice);
		return (M)this;
	}
	
	public java.lang.String getExperice() {
		return getStr("experice");
	}

	public M setPostion(java.lang.String postion) {
		set("postion", postion);
		return (M)this;
	}
	
	public java.lang.String getPostion() {
		return getStr("postion");
	}

	public M setValidity(java.util.Date Validity) {
		set("Validity", Validity);
		return (M)this;
	}
	
	public java.util.Date getValidity() {
		return get("Validity");
	}

	public M setRealseTime(java.util.Date realseTime) {
		set("realse_time", realseTime);
		return (M)this;
	}
	
	public java.util.Date getRealseTime() {
		return get("realse_time");
	}

	public M setNumber(java.lang.Integer number) {
		set("number", number);
		return (M)this;
	}
	
	public java.lang.Integer getNumber() {
		return getInt("number");
	}

	public M setWorkpalce(java.lang.String workpalce) {
		set("workpalce", workpalce);
		return (M)this;
	}
	
	public java.lang.String getWorkpalce() {
		return getStr("workpalce");
	}

	public M setAttachment(java.lang.String attachment) {
		set("attachment", attachment);
		return (M)this;
	}
	
	public java.lang.String getAttachment() {
		return getStr("attachment");
	}

	public M setCompany(java.lang.String company) {
		set("company", company);
		return (M)this;
	}
	
	public java.lang.String getCompany() {
		return getStr("company");
	}

	public M setEducation(java.lang.String education) {
		set("education", education);
		return (M)this;
	}
	
	public java.lang.String getEducation() {
		return getStr("education");
	}

	public M setDescription(java.lang.String description) {
		set("description", description);
		return (M)this;
	}
	
	public java.lang.String getDescription() {
		return getStr("description");
	}

	public M setCategory(java.lang.String category) {
		set("category", category);
		return (M)this;
	}
	
	public java.lang.String getCategory() {
		return getStr("category");
	}

	public M setPhone(java.lang.String phone) {
		set("phone", phone);
		return (M)this;
	}
	
	public java.lang.String getPhone() {
		return getStr("phone");
	}

	public M setSalary(java.lang.Integer salary) {
		set("salary", salary);
		return (M)this;
	}
	
	public java.lang.Integer getSalary() {
		return getInt("salary");
	}

}
