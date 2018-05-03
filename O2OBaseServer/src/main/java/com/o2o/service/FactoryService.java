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
	
    private static final RentFactory dao=new RentFactory().dao();
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
		rentFactory.save();	
	}
}