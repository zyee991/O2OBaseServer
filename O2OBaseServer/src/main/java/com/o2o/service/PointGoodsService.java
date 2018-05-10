package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.PointsGift;

public class PointGoodsService {

	private static PointsGift dao=new PointsGift().dao();
	public List<Record> tableData() {
		List<Record>list=Db.find("select* from tb_points_gift");
		return list;
	}

	public void deleteById(String id) {
		dao.deleteById(id);
		
	}

	
}
