package com.o2o.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbShangjiaoperation<M extends BaseTbShangjiaoperation<M>> extends Model<M> implements IBean {

	public M setShopGoodsId(java.lang.String shopGoodsId) {
		set("shop_goods_id", shopGoodsId);
		return (M)this;
	}
	
	public java.lang.String getShopGoodsId() {
		return getStr("shop_goods_id");
	}

	public M setShopId(java.lang.String shopId) {
		set("shop_id", shopId);
		return (M)this;
	}
	
	public java.lang.String getShopId() {
		return getStr("shop_id");
	}

	public M setGoodsinfoId(java.lang.String goodsinfoId) {
		set("goodsinfo_id", goodsinfoId);
		return (M)this;
	}
	
	public java.lang.String getGoodsinfoId() {
		return getStr("goodsinfo_id");
	}

}
