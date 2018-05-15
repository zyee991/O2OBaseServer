package com.o2o.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.common.model.City;
import com.o2o.common.model.District;
import com.o2o.common.model.Province;
import com.o2o.common.model.Realshop;
import com.o2o.common.model.Sectype;

public class RealShopService {
	
	private static final Realshop dao=new Realshop().dao();
    private static final Province provincedao=new Province().dao();
    private static final City citydao=new City().dao();
    private static final District districtdao=new District().dao();
    private static final Sectype sectypedao=new Sectype().dao();
	public Page<Record> paginage(int i, int j) {
	Page<Record> page=Db.paginate(i,j,"select a.*,d.district_name,t.sec_type_name","from tb_district d,tb_sec_type t,tb_realshop a"
			+ " where a.district_id=d.district_id and t.sec_type_id=a.realshop_type");
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

	public List<Sectype> getTypeList() {
		return sectypedao.find("select*from tb_sec_type where first_type_id=6");
	}

	public void save(Realshop realshop) {
		try{
			realshop.save();
		}catch(Exception e){
			realshop.update();
		}
		
	}

	public List<Record> getdictlist(String district) {
		List<Record>list=Db.find("select a.*,b.city_name,c.province_name from tb_district a,tb_city b,tb_province c where a.city_id=b.city_id and "
				+ "c.province_id=b.province_id and district_id="+district);
		return list;
	}

	public Realshop getshopById(String shpid) {
		return dao.findById(shpid);
	}

	public List<Record> getTypeList(String typeid) {
		
		List<Record> typelist=Db.find("select* from tb_sec_type where sec_type_id=?",typeid);
		return typelist;
	}

	public void deleteById(String id) {
		dao.deleteById(id);
		
	}

	public List<Record> tableData() {
		String wheresql=" where a.district_id=d.district_id and t.sec_type_id=a.realshop_type";
		List<Record>list=Db.find("select a.*,d.district_name,t.sec_type_name from tb_district d,tb_sec_type t,tb_realshop a"+wheresql);
		return list;
	}

}
