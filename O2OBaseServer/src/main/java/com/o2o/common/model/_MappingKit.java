package com.o2o.common.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("tb_base_manager", "id", Manager.class);
		arp.addMapping("tb_base_manager_role", "id", ManagerRole.class);
		arp.addMapping("tb_base_navigation", "id", Navigation.class);
		arp.addMapping("tb_base_role", "id", Role.class);
		arp.addMapping("tb_base_role_navigation", "id", RoleNavigation.class);
	}
}

