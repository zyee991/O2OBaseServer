package com.o2o.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.o2o.common.model.ManagerRole;


public class ManagerRoleService {
	private static final ManagerRole dao = new ManagerRole().dao();

	public void save(String managerId, String roleIds) {
		String[] roleIdArray = roleIds.split(",");
		String sqlPara = "select rn.* from tb_base_manager_role as rn where "
				+ "rn.managerId = ? ";
		
		List<ManagerRole> managerRoleList = dao.find(sqlPara,managerId);
		List<String> idValues = new ArrayList<>();
		for(ManagerRole managerRole : managerRoleList) {
			idValues.add(managerRole.getId());
		}
		if(idValues.size() > 0 ){
			dao.deleteById(idValues.toArray());
		}
		for(String roleId : roleIdArray) {
			if(StringUtils.isNotBlank(roleId)) {
				ManagerRole managerRole = new ManagerRole();
				managerRole.setId(UUID.randomUUID().toString());
				managerRole.setManagerId(managerId);
				managerRole.setRoleId(roleId);
				managerRole.save();
			}
		}
	}

}
