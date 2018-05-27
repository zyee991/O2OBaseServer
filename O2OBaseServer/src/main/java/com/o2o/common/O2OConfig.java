package com.o2o.common;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.template.Engine;
import com.o2o.common.model._MappingKit;
import com.o2o.index.IndexController;
import com.o2o.interceptor.LoginInterceptor;
import com.o2o.web.EchartsController;
import com.o2o.web.EmployeeController;
import com.o2o.web.FactoryController;
import com.o2o.web.FactoryPactController;
import com.o2o.web.FileController;
import com.o2o.web.GoodsOrderController;
import com.o2o.web.GoodsTypeController;
import com.o2o.web.GoodsinfoController;
import com.o2o.web.MUserController;
import com.o2o.web.ManagerController;
import com.o2o.web.NavigationController;
import com.o2o.web.PointGoodsController;
import com.o2o.web.PointsRecordController;
import com.o2o.web.QuickOperationController;
import com.o2o.web.RealShopController;
import com.o2o.web.RecruitController;
import com.o2o.web.RoleController;
import com.o2o.web.ServiceController;
import com.o2o.web.ServiceOrderController;
import com.o2o.web.ShopController;
import com.o2o.web.TaocanController;
import com.o2o.web.TechnologyController;
import com.o2o.web.ToolController;
import com.o2o.web.ToolsPactController;
import com.o2o.web.TreeController;
import com.o2o.web.WUserController;
import com.o2o.web.WxHeadController;
import com.o2o.websocket.WebSocketHandler;

import cn.dreampie.quartz.QuartzPlugin;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * API引导式配置
 */
public class O2OConfig extends JFinalConfig {
	
	/**
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 * 
	 * 使用本方法启动过第一次以后，会在开发工具的 debug、run config 中自动生成
	 * 一条启动配置，可对该自动生成的配置再添加额外的配置项，例如 VM argument 可配置为：
	 * -XX:PermSize=64M -XX:MaxPermSize=256M
	 */
	public static void main(String[] args) {
		/**
		 * 特别注意：Eclipse 之下建议的启动方式
		 */
		JFinal.start("src/main/webapp", 8080, "/", 5);
		
		/**
		 * 特别注意：IDEA 之下建议的启动方式，仅比 eclipse 之下少了最后一个参数
		 */
		// JFinal.start("src/main/webapp", 80, "/");
	}
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		PropKit.use("application.properties");
		me.setDevMode(PropKit.getBoolean("devMode", false));
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/", IndexController.class, "/WEB-INF/view/index");	// 第三个参数为该Controller的视图存放路径
		me.add("/navigation", NavigationController.class,"/WEB-INF/view/navigation");// 第三个参数省略时默认与第一个参数值相同，在此即为 "/blog"
		me.add("/manager", ManagerController.class,"/WEB-INF/view/manager");// 第三个参数省略时默认与第一个参数值相同，在此即为 "/blog"
		me.add("/role", RoleController.class,"/WEB-INF/view/role");// 第三个参数省略时默认与第一个参数值相同，在此即为 "/blog"
		me.add("/tree", TreeController.class);// 第三个参数省略时默认与第一个参数值相同，在此即为 "/blog"
		me.add("/goods_info", GoodsinfoController.class,"WEB-INF/view/goods_info");
		me.add("/taocan", TaocanController.class,"WEB-INF/view/taocan");
		me.add("/goods_type", GoodsTypeController.class,"WEB-INF/view/goods_type");
		me.add("/service",ServiceController.class,"WEB-INF/view/service");
		me.add("/service_order",ServiceOrderController.class,"WEB-INF/view/service_order");
		me.add("/goods_order",GoodsOrderController.class,"WEB-INF/view/goods_order");
		me.add("/file",FileController.class,"WEB-INF/view");
		me.add("/shop",ShopController.class,"WEB-INF/view/shop");
		me.add("/realshop",RealShopController.class,"WEB-INF/view/realshop");
		me.add("/employee", EmployeeController.class,"WEB-INF/view/employee");
		me.add("/recruit",RecruitController.class,"WEB-INF/view/recruit");
		me.add("/factory",FactoryController.class,"WEB-INF/view/factory");
		me.add("/tools",ToolController.class,"WEB-INF/view/tool");
		me.add("/quickOperation", QuickOperationController.class,"WEB-INF/view/quick_operation");
		me.add("/factoryPact",FactoryPactController.class,"WEB-INF/view/factorypact");
		me.add("/pacttools",ToolsPactController.class,"WEB-INF/view/toolpact");
		me.add("/echarts",EchartsController.class,"WEB-INF/view/echarts");
		me.add("/pointsgoods",PointGoodsController.class,"WEB-INF/view/pointsgoods");
		me.add("/exchangelogs",PointsRecordController.class,"WEB-INF/view/pointrecord");
		me.add("/WUser",WUserController.class,"WEB-INF/view/WUser");
		me.add("/MUser",MUserController.class,"WEB-INF/view/MUser");
		me.add("/technology",TechnologyController.class,"WEB-INF/view/technology");
		me.add("/WxHead",WxHeadController.class,"WEB-INF/view/WxHead");
	}
	
	public void configEngine(Engine me) {
		me.addSharedFunction("/WEB-INF/view/common/_layout.html");
		me.addSharedFunction("/WEB-INF/view/common/_paginate.html");
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置 druid 数据库连接池插件
		DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password"));
		me.add(druidPlugin);
		
		// ehcache插件
		EhCachePlugin ehCachePlugin = new EhCachePlugin();
		me.add(ehCachePlugin);
		
//		QuartzPlugin quartzPlugin = new QuartzPlugin();
//		quartzPlugin.setJobs("quartz_job.properties");
//		me.add(quartzPlugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		arp.setShowSql(false);
		arp.setDevMode(false);
		// 所有映射在 MappingKit 中自动化搞定
		_MappingKit.mapping(arp);
		me.add(arp);
	}
	
	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		me.add(new LoginInterceptor());
//		me.add(new NavigationInterceptor());
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		me.add(new WebSocketHandler("^/wsmsg"));
	}
}
