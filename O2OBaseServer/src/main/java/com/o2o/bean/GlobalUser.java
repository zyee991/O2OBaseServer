package com.o2o.bean;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class GlobalUser {
	private String id;
	private String name;
	
	/**
	 * 1：平台用户 （管理）
	 * 2：微信用户 （客户）
	 * 3：app 用户 （技师）
	 */
	private Long type;
	
	public static GlobalUser findOne(String id) {
		GlobalUser globalUser = null;
		Record record = Db.findFirst("select * from view_global_user where id=?",id);
		if(record != null) {
			globalUser = new GlobalUser(record);
		}
		
		return globalUser;
	}

	public GlobalUser() {
	}

	public GlobalUser(String id) {
		Record record = Db.findFirst("select * from view_global_user where id=?",id);
		if(record != null) {
			this.id = (String) record.get("id");
			this.name = (String) record.get("name");
			this.type = (Long) record.get("type");
		}
	}

	public GlobalUser(String id, String name, Long type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}
	
	public GlobalUser(Record map) {
		this.id = (String) map.get("id");
		this.name = (String) map.get("name");
		this.type = (Long) map.get("type");
	}

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Long getType() {
		return type;
	}



	public void setType(Long type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "GlobalUser [id=" + id + ", name=" + name + ", type=" + type + "]";
	}
	
	
}
