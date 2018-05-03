package com.o2o.web;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.o2o.service.OrderService;
import com.o2o.util.HttpUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GoodsOrderController extends Controller {

	static OrderService orderService=new OrderService();
	public void index(){
		setAttr("title","商品订单管理");
		String paystatus=getPara("order_pay_status");
		String orderstatus=getPara("order_status");
		String anotherstatus=getPara("another_status");
		System.out.println(paystatus+"---------------"+orderstatus);
		Page<Record>page=orderService.paginate(getParaToInt(0, 1), 10,paystatus,orderstatus,anotherstatus);
		setAttr("page", page);
		render("index.html");
	}
	
   public void confirm(){
       System.out.println("请求方法");
/*	   String data="[{name:'message',value:'发送成功'},{name:'success',value:true}]";*/
       String mobanurl="http://b6bf2e87.ngrok.io:80/MySSM/template/getTempList";
       String mobanresult=HttpUtils.doGet(mobanurl);
       JSONObject json=JSONObject.fromObject(mobanresult);
       JSONArray jsonarray=json.getJSONArray("template_list");
       String mobanid="";
       String title="订单完成通知";
       for(int i=0;i<jsonarray.size();i++){
    	   JSONObject o1 = (JSONObject) jsonarray.get(i);
    	   if(o1.getString("title").equals(title)){//jsonarray[i].get("title").equals("")
    		   mobanid=o1.getString("template_id");
    		   break;
    	   }
       }
       System.out.println(mobanresult+"-----------------mobanresult");
       System.out.println(mobanid+"-----------------mobanid");
       String messageurl="http://b6bf2e87.ngrok.io:80/MySSM/template/sendtemp?user_openid=oSlHa0tnZTMhJ3AfDqAv1VNNizbQ&templete_id=D4EFsH4XETVpkgeW6qdvJadbozYjgskbqd0HoI9OuN4&nickname=张三&createtime=2018-05-01 22:46&ordertatus=已收货";
       String messageresult=HttpUtils.doGet(messageurl);
       System.out.println(messageresult+"-----------------------------------------messageresult");
	   String data="{'message':'发送成功','success':true}";
	   
	   renderJson(data);
   }
}
