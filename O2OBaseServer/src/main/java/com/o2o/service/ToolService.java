package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.City;
import com.o2o.common.model.District;
import com.o2o.common.model.Province;
import com.o2o.common.model.RentTools;

public class ToolService {

	private static final RentTools dao=new RentTools().dao();
	private static final Province provincedao=new Province().dao();
	private static final City citydao=new City().dao();
	private static final District districtdao=new District().dao();
	public Page<Record> paginate(Integer paraToInt, int i) {
		String wheresql=" where t.address_tid=d.district_id and s.sec_type_id=t.typ_id";
		Page<Record>page=Db.paginate(paraToInt, i, "select t.*,d.district_name,s.sec_type_name as type_name"," from tb_rent_tools t,tb_district d,tb_sec_type s"
				+wheresql);
		return page;
	}
	public List<Province> getProvinceList() {
		return provincedao.find("select* from tb_province");
	}

	public List<City> findByProvinceId(String provinceId) {
		return citydao.find("select*from tb_city where province_id=?",provinceId);
	}

	public List<District> findByCityId(String cityId) {
		
		return districtdao.find("select*from tb_district where city_id=?",cityId);
	}
	public List<Record> tableData() {
		String wheresql=" where t.address_tid=d.district_id";
		List<Record>list=Db.find("select t.*,d.district_name from tb_rent_tools t,tb_district d");
		return list;
	}
	public List<Record> reload(List<Record> list) {
		for(Record record:list) {
			if(record.get("state").equals(0)) {
				record.set("status_name", "未租");
			} else {
				record.set("status_name", "在租中");
			}
		}
		return list;
	}
}
