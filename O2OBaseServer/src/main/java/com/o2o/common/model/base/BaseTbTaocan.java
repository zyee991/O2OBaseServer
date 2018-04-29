package com.o2o.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbTaocan<M extends BaseTbTaocan<M>> extends Model<M> implements IBean {

	public M setTaoGoodId(java.lang.String taoGoodId) {
		set("tao_good_id", taoGoodId);
		return (M)this;
	}
	
	public java.lang.String getTaoGoodId() {
		return getStr("tao_good_id");
	}

	public M setTaocanId(java.lang.String taocanId) {
		set("taocan_id", taocanId);
		return (M)this;
	}
	
	public java.lang.String getTaocanId() {
		return getStr("taocan_id");
	}

	public M setGoodsinfoId(java.lang.String goodsinfoId) {
		set("goodsinfo_id", goodsinfoId);
		return (M)this;
	}
	
	public java.lang.String getGoodsinfoId() {
		return getStr("goodsinfo_id");
	}

	public M setItemCount(java.lang.Integer itemCount) {
		set("item_count", itemCount);
		return (M)this;
	}
	
	public java.lang.Integer getItemCount() {
		return getInt("item_count");
	}

}
