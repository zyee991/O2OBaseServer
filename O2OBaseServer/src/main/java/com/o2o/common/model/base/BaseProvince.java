package com.o2o.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseProvince<M extends BaseProvince<M>> extends Model<M> implements IBean {

	public M setProvinceId(java.lang.String provinceId) {
		set("province_id", provinceId);
		return (M)this;
	}
	
	public java.lang.String getProvinceId() {
		return getStr("province_id");
	}

	public M setProvinceName(java.lang.String provinceName) {
		set("province_name", provinceName);
		return (M)this;
	}
	
	public java.lang.String getProvinceName() {
		return getStr("province_name");
	}

}
