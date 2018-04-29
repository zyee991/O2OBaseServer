package com.o2o.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbShopcar<M extends BaseTbShopcar<M>> extends Model<M> implements IBean {

	public M setShopcarId(java.lang.Integer shopcarId) {
		set("shopcar_id", shopcarId);
		return (M)this;
	}
	
	public java.lang.Integer getShopcarId() {
		return getInt("shopcar_id");
	}

	public M setUserOpenid(java.lang.String userOpenid) {
		set("user_openid", userOpenid);
		return (M)this;
	}
	
	public java.lang.String getUserOpenid() {
		return getStr("user_openid");
	}

	public M setShopId(java.lang.String shopId) {
		set("shop_id", shopId);
		return (M)this;
	}
	
	public java.lang.String getShopId() {
		return getStr("shop_id");
	}

	public M setShopNum(java.lang.Integer shopNum) {
		set("shop_num", shopNum);
		return (M)this;
	}
	
	public java.lang.Integer getShopNum() {
		return getInt("shop_num");
	}

}
