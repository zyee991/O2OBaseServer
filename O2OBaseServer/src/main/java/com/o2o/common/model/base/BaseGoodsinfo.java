package com.o2o.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseGoodsinfo<M extends BaseGoodsinfo<M>> extends Model<M> implements IBean {

	public M setGoodsinfoId(java.lang.String goodsinfoId) {
		set("goodsinfo_id", goodsinfoId);
		return (M)this;
	}
	
	public java.lang.String getGoodsinfoId() {
		return getStr("goodsinfo_id");
	}

	public M setSecTypeId(java.lang.String secTypeId) {
		set("sec_type_id", secTypeId);
		return (M)this;
	}
	
	public java.lang.String getSecTypeId() {
		return getStr("sec_type_id");
	}

	public M setGoodsinfoName(java.lang.String goodsinfoName) {
		set("goodsinfo_name", goodsinfoName);
		return (M)this;
	}
	
	public java.lang.String getGoodsinfoName() {
		return getStr("goodsinfo_name");
	}

	public M setGoodsinfoPoints(java.lang.Integer goodsinfoPoints) {
		set("goodsinfo_points", goodsinfoPoints);
		return (M)this;
	}
	
	public java.lang.Integer getGoodsinfoPoints() {
		return getInt("goodsinfo_points");
	}

	public M setGoodsinfoPrimaryPrice(java.lang.Float goodsinfoPrimaryPrice) {
		set("goodsinfo_primary_price", goodsinfoPrimaryPrice);
		return (M)this;
	}
	
	public java.lang.Float getGoodsinfoPrimaryPrice() {
		return getFloat("goodsinfo_primary_price");
	}

	public M setGoodsinfoPointsFlag(java.lang.Boolean goodsinfoPointsFlag) {
		set("goodsinfo_points_flag", goodsinfoPointsFlag);
		return (M)this;
	}
	
	public java.lang.Boolean getGoodsinfoPointsFlag() {
		return get("goodsinfo_points_flag");
	}

	public M setGoodsinfoExpiration(java.util.Date goodsinfoExpiration) {
		set("goodsinfo_expiration", goodsinfoExpiration);
		return (M)this;
	}
	
	public java.util.Date getGoodsinfoExpiration() {
		return get("goodsinfo_expiration");
	}

	public M setGoodsinfoFoever(java.lang.Boolean goodsinfoFoever) {
		set("goodsinfo_foever", goodsinfoFoever);
		return (M)this;
	}
	
	public java.lang.Boolean getGoodsinfoFoever() {
		return get("goodsinfo_foever");
	}

	public M setGoodsinfoImage(java.lang.String goodsinfoImage) {
		set("goodsinfo_image", goodsinfoImage);
		return (M)this;
	}
	
	public java.lang.String getGoodsinfoImage() {
		return getStr("goodsinfo_image");
	}

	public M setGoodsinfoDesc(java.lang.String goodsinfoDesc) {
		set("goodsinfo_desc", goodsinfoDesc);
		return (M)this;
	}
	
	public java.lang.String getGoodsinfoDesc() {
		return getStr("goodsinfo_desc");
	}

	public M setGoodsinfoDescdetail(java.lang.String goodsinfoDescdetail) {
		set("goodsinfo_descdetail", goodsinfoDescdetail);
		return (M)this;
	}
	
	public java.lang.String getGoodsinfoDescdetail() {
		return getStr("goodsinfo_descdetail");
	}

	public M setGoodsinfoIstaocan(java.lang.Boolean goodsinfoIstaocan) {
		set("goodsinfo_istaocan", goodsinfoIstaocan);
		return (M)this;
	}
	
	public java.lang.Boolean getGoodsinfoIstaocan() {
		return get("goodsinfo_istaocan");
	}

}
