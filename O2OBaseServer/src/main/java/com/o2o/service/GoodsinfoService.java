package com.o2o.service;

import java.util.List;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.Goodsinfo;
import com.o2o.common.model.Sectype;

public class GoodsinfoService {

	private static final Goodsinfo dao = new Goodsinfo().dao();
	private static final Sectype typedao = new Sectype().dao();

	// 查询商品
	public Page<Goodsinfo> paginate(int pageNumber, int pageSize) {
		return dao.paginate(pageNumber, pageSize, "select*", "from tb_goodsinfo where goodsinfo_istaocan = 0");
	}

	// 查询套餐
	public Page<Goodsinfo> paginateTaocan(int pageNumber, int pageSize) {
		return dao.paginate(pageNumber, pageSize, "select*", "from tb_goodsinfo where goodsinfo_istaocan = 1");
	}

	public List<Record> findGoodListById(String id) {
		String wheresql = " where a.sec_type_id=t.sec_type_id and goodsinfo_id='" + id + "'";
		List<Record> list = Db.find("select a.*,t.sec_type_name from tb_goodsinfo a,tb_sec_type t" + wheresql);
		return list;
	}

	public void deleteById(String id) {
		dao.deleteById(id);
	}

	public void save(Goodsinfo goodsinfo) {

		try {
			goodsinfo.save();
		} catch (Exception e) {
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
						+ "and sec_type_id=? and shop_istaocan=false", typeId);
		return goodinfoList;
	}

	public List<Record> findTaocanByTaocanId(String taocanId) {
		String sql = "SELECT tb_taocan.tao_good_id, tb_taocan.taocan_id,tb_taocan.goodsinfo_id,tb_taocan.item_count,tb_sec_type.sec_type_name,"
				+ "tb_goodsinfo.sec_type_id,tb_goodsinfo.goodsinfo_name FROM tb_taocan"
				+ " INNER JOIN tb_shangjiaoperation ON tb_taocan.goodsinfo_id = tb_shangjiaoperation.shop_id"
				+ " INNER JOIN tb_goodsinfo ON tb_shangjiaoperation.goodsinfo_id = tb_goodsinfo.goodsinfo_id"
				+ " INNER JOIN tb_sec_type ON tb_goodsinfo.sec_type_id = tb_sec_type.sec_type_id"
				+ " where tb_taocan.taocan_id=? ";
		List<Record> taocanList = Db.find(sql, taocanId);
		return taocanList;
	}

	public List<Record> tableData() {
		String wheresql = " where a.sec_type_id=t.sec_type_id and a.goodsinfo_istaocan=0";
		List<Record> list = Db.find("select a.*,t.sec_type_name from tb_goodsinfo a,tb_sec_type t" + wheresql);
		return list;
	}

	public List<Record> tableDataofTaocan() {
		String wheresql = " where a.sec_type_id=t.sec_type_id and a.goodsinfo_istaocan=1";
		List<Record> list = Db.find("select a.*,t.sec_type_name from tb_goodsinfo a,tb_sec_type t" + wheresql);
		return list;
	}

	public Goodsinfo findById(String id) {

		return dao.findById(id);
	}
}
