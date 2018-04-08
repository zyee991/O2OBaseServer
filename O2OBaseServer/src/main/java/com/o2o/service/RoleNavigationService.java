package com.o2o.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.o2o.common.model.RoleNavigation;

public class RoleNavigationService {
	private static final RoleNavigation dao = new RoleNavigation().dao();

	public void save(String roleId, String navigationIds) {
		String[] navigationIdArray = navigationIds.split(",");
		String sqlPara = "select rn.* from tb_base_role_navigation as rn where "
				+ "rn.roleId = ? ";
		
		List<RoleNavigation> roleNavigationList = dao.find(sqlPara,roleId);
		List<String> idValues = new ArrayList<>();
		for(RoleNavigation roleNavigation : roleNavigationList) {
			idValues.add(roleNavigation.getId());
		}
		if(idValues.size() > 0 ){
			dao.deleteById(idValues.toArray());
		}
		for(String navigationId : navigationIdArray) {
			if(StringUtils.isNotBlank(navigationId)) {
				RoleNavigation roleNavigation = new RoleNavigation();
				roleNavigation.setId(UUID.randomUUID().toString());
				roleNavigation.setRoleId(roleId);
				roleNavigation.setNavigationId(navigationId);
				roleNavigation.save();
			}
		}
	}

}
