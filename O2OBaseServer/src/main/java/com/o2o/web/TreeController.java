package com.o2o.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.o2o.common.model.Manager;
import com.o2o.common.model.Navigation;
import com.o2o.common.model.Role;
import com.o2o.service.NavigationService;
import com.o2o.service.RoleService;
import com.o2o.util.BaseUtils;

public class TreeController extends Controller{
	
	static NavigationService navigationService = new NavigationService();
	static RoleService roleService=new RoleService();
	public void navigationTree() {
		String roleId = getPara("roleId");
		Map<String,Object> checked = new HashMap<>();
		checked.put("checked", true);
		Manager manager = BaseUtils.getManager(this);
		List<Map<String,Object>> treelist=new ArrayList<>();
		List<Navigation> parentlist=navigationService.findParentNavigationByManager(manager);
		List<Navigation> checkedNavigationList=navigationService.findNavigationByRoleId(roleId);

		
		for(Navigation oneparentmap:parentlist){
			Map<String,Object> ParentMap=new HashMap<String,Object>();
			if(checkedNavigationList.contains(oneparentmap)) {
				ParentMap.put("state", checked);
			}
			ParentMap.put("id",oneparentmap.getId().toString());
			ParentMap.put("text", oneparentmap.getName());
			List<Navigation> childlist=navigationService.findChildNavigationByParentId(oneparentmap.getId());

			List<Map<String,Object>> childMapList = new ArrayList<>();
			for(Navigation child : childlist) {
				Map<String,Object> childMap = new HashMap<>();
				if(checkedNavigationList.contains(child)) {
					childMap.put("state", checked);
				}
				childMap.put("id",child.getId());
				childMap.put("text",child.getName());
				childMapList.add(childMap);
			}
			ParentMap.put("nodes",childMapList);
			treelist.add(ParentMap);
		}
		renderJson(treelist);
	}
	
	public void roleTree() {
		Map<String,Object> checked = new HashMap<>();
		checked.put("checked", true);
		Manager manager = BaseUtils.getManager(this);
		List<Map<String,Object>> treelist=new ArrayList<>();
		List<Role> roleList=roleService.findAllRoleByManager();
		List<Role> checkedRoleList=roleService.findRoleByManager(manager);
		
		for(Role role : roleList) {
			Map<String, Object> roleMap = new HashMap<>();
			if(checkedRoleList.contains(role)) {
				roleMap.put("state", checked);
			}
			roleMap.put("id",role.getId().toString());
			roleMap.put("text", role.getName());
			treelist.add(roleMap);
		}
		
		renderJson(treelist);
	}
}
