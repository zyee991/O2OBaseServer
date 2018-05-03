package com.o2o.service;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.Goodsinfo;
import com.o2o.common.model.Sectype;
import com.o2o.common.model.Taocan;

public class GoodsinfoService {

	private static final Goodsinfo dao = new Goodsinfo().dao();
	private static final Sectype typedao = new Sectype().dao();
	private static final Taocan taocanDao = new Taocan().dao();

	// 查询商品
	public Page<Goodsinfo> paginate(int pageNumber, int pageSize) {
		return dao.paginate(pageNumber, pageSize, "select*", "from tb_goodsinfo where goodsinfo_istaocan = 0");
	}

	// 查询套餐
	public Page<Goodsinfo> paginateTaocan(int pageNumber, int pageSize) {
		return dao.paginate(pageNumber, pageSize, "select*", "from tb_goodsinfo where goodsinfo_istaocan = 1");
	}

	public Goodsinfo findById(String id) {
		return dao.findById(id);
	}

	public void deleteById(String id) {
		dao.deleteById(id);
	}

	public void save(Goodsinfo goodsinfo) {

		try {
			goodsinfo.save();
		} catch (Exception e) {
			e.printStackTrace();
			goodsinfo.update();
		}
	}

	public List<Sectype> getTypeList() {
		List<Sectype> typelist = typedao.find("select a.* from tb_sec_type a where a.first_type_id=1");
		/*
		 * Gson gson=new Gson(); String str=gson.toJson(typelist);
		 */
		return typelist;
	}

	public List<Sectype> getTypeListForTaocanSelect() {
		List<Sectype> typelist = typedao
				.find("select a.* from tb_sec_type a where a.first_type_id=1 and a.sec_type_id <> 0");
		/*
		 * Gson gson=new Gson(); String str=gson.toJson(typelist);
		 */
		return typelist;
	}

	public List<Goodsinfo> findByTypeId(String typeId) {
		List<Goodsinfo> goodinfoList = dao
				.find("select * from tb_shop p,tb_goodsinfo g,tb_shangjiaoperation s where g.goodsinfo_id=s.goodsinfo_id and p.shop_id=s.shop_id "
						+ "and sec_type_id=? and shop_istaocan=true", typeId);
		return goodinfoList;
	}

	public List<Record> findTaocanByTaocanId(String taocanId) {
		List<Record> taocanList = Db.find(
				"select tg.*,g.goodsinfo_name,s.sec_type_name,s.sec_type_id from tb_taocan as tg join tb_goodsinfo"
				+ " as g on tg.goodsinfo_id = g.goodsinfo_id join tb_sec_type as s on"
				+ " g.sec_type_id = s.sec_type_id where tg.taocan_id = ?",
				taocanId);
		return taocanList;
	}
}
