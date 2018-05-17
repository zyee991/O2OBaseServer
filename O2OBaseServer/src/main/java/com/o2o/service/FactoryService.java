package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.City;
import com.o2o.common.model.District;
import com.o2o.common.model.Province;
import com.o2o.common.model.RentFactory;

public class FactoryService {
	
    private static final Province provincedao=new Province().dao();
    private static final City citydao=new City().dao();
    private static final District districtdao=new District().dao();
	public Page<Record> paginate(Integer i, int j) {
		Page<Record>page=Db.paginate(i,j,"select f.*,d.district_name","from tb_district d,tb_rent_factory f"
				+ " where f.address_pid=d.district_id");
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
	
	public void save(RentFactory rentFactory) {
		try{
			rentFactory.save();
		}catch(Exception e){
			rentFactory.update();
		}
		
	}
	
	public List<Record> findById(String id){
		String wheresql=" where a.address_pid=v.district_id";
		List<Record>list=Db.find("select*from tb_rent_factory a,view_address v"+wheresql);
		return list;
	}

	public List<Record> tableData() {
		String wheresql=" where f.address_pid=d.district_id";
		List<Record>list=Db.find("select f.*,d.* from view_address d,tb_rent_factory f"+wheresql);
		return list;
	}

	public List<Record> reload(List<Record> list) {
			for(Record record:list) {
				if(record.get("state").equals(false) ) {
					record.set("status_name", "未租");
				} else if (record.get("state").equals(true)) {
					record.set("status_name", "已租");
				} 
				String province_name=record.get("province_name");
				String city_name=record.get("city_name");
				String district_name=record.get("district_name");
				String area=province_name+" "+city_name+" "+district_name;
				record.set("area", area);

			}
		return list;
	}
}
