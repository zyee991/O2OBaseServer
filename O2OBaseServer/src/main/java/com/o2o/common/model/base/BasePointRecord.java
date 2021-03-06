package com.o2o.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BasePointRecord<M extends BasePointRecord<M>> extends Model<M> implements IBean {

	public M setPointRecordId(java.lang.String pointRecordId) {
		set("point_record_id", pointRecordId);
		return (M)this;
	}
	
	public java.lang.String getPointRecordId() {
		return getStr("point_record_id");
	}

	public M setUserOpenid(java.lang.String userOpenid) {
		set("user_openid", userOpenid);
		return (M)this;
	}
	
	public java.lang.String getUserOpenid() {
		return getStr("user_openid");
	}

	public M setPointRecordTime(java.util.Date pointRecordTime) {
		set("point_record_time", pointRecordTime);
		return (M)this;
	}
	
	public java.util.Date getPointRecordTime() {
		return get("point_record_time");
	}

	public M setPointCount(java.lang.Integer pointCount) {
		set("point_count", pointCount);
		return (M)this;
	}
	
	public java.lang.Integer getPointCount() {
		return getInt("point_count");
	}

	public M setPointFrom(java.lang.Integer pointFrom) {
		set("point_from", pointFrom);
		return (M)this;
	}
	
	public java.lang.Integer getPointFrom() {
		return getInt("point_from");
	}

	public M setPointFromId(java.lang.String pointFromId) {
		set("point_from_id", pointFromId);
		return (M)this;
	}
	
	public java.lang.String getPointFromId() {
		return getStr("point_from_id");
	}

	public M setPointRecordStatus(java.lang.Integer pointRecordStatus) {
		set("point_record_status", pointRecordStatus);
		return (M)this;
	}
	
	public java.lang.Integer getPointRecordStatus() {
		return getInt("point_record_status");
	}

}
