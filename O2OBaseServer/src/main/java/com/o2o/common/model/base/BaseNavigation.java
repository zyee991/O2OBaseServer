package com.o2o.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseNavigation<M extends BaseNavigation<M>> extends Model<M> implements IBean {

	public M setId(java.lang.String id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.String getId() {
		return getStr("id");
	}

	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public M setUrl(java.lang.String url) {
		set("url", url);
		return (M)this;
	}
	
	public java.lang.String getUrl() {
		return getStr("url");
	}

	public M setParentId(java.lang.String parentId) {
		set("parentId", parentId);
		return (M)this;
	}
	
	public java.lang.String getParentId() {
		return getStr("parentId");
	}

	public M setCreateDate(java.util.Date createDate) {
		set("createDate", createDate);
		return (M)this;
	}
	
	public java.util.Date getCreateDate() {
		return get("createDate");
	}

	public M setModifyDate(java.util.Date modifyDate) {
		set("modifyDate", modifyDate);
		return (M)this;
	}
	
	public java.util.Date getModifyDate() {
		return get("modifyDate");
	}

	public M setSystemName(java.lang.String systemName) {
		set("systemName", systemName);
		return (M)this;
	}
	
	public java.lang.String getSystemName() {
		return getStr("systemName");
	}

	public M setUseFlag(java.lang.Boolean useFlag) {
		set("useFlag", useFlag);
		return (M)this;
	}
	
	public java.lang.Boolean getUseFlag() {
		return get("useFlag");
	}

	public M setSortNumber(java.lang.Integer sortNumber) {
		set("sortNumber", sortNumber);
		return (M)this;
	}
	
	public java.lang.Integer getSortNumber() {
		return getInt("sortNumber");
	}

}
