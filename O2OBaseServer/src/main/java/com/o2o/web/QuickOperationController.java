package com.o2o.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.o2o.bean.GlobalUser;
import com.o2o.common.model.Message;
import com.o2o.message.MessageFactory;
import com.o2o.service.MessageService;
import com.o2o.util.BaseUtils;
import com.o2o.websocket.WebSocketEndpoint;

public class QuickOperationController extends Controller {
	
	static MessageService service = new MessageService();
	
	public void msg() {
		setAttr("title","消息");
		render("msg.html");
	}
	
	public void msgTableData() {
		List<Message> messageList = service.msgTableData();
		renderJson(messageList);
	}
	
	
	public void chat() {
		String sessionId = getPara("sessionId");
		GlobalUser user = WebSocketEndpoint.USER_MAP.get(sessionId);
		setAttr("user",user);
		setAttr("title",user.getName());
		String userId1 = user.getId();
		String userId2 = BaseUtils.getManager(this).getId();
		List<Message> messageList = service.messageList(userId1,userId2);
		setAttr("messageList",messageList);
		render("chat.html");
	}
	
	public void online() {
		setAttr("title","用户");
		render("online.html");
	}
	
	public void onlineTableData() {
		Map<String,GlobalUser> map = WebSocketEndpoint.USER_MAP;
		List<Map<String,Object>> resultMapList = new ArrayList<Map<String,Object>>();
		map.forEach((key,value)->{
			Map<String,Object> resultMap = new HashMap<>();
			resultMap.put("sessionId", key);
			resultMap.put("userId", value.getId());
			resultMap.put("name", value.getName());
			resultMap.put("type", value.getType());
			resultMapList.add(resultMap);
		});
		renderJson(resultMapList);
	}
	
	public void sendMessage() {
		String receiver = getPara("receiver");
		String content = getPara("content");
		Message message = MessageFactory.createMessage("消息", BaseUtils.getManager(this).getId(), receiver, null, null, content, null);
		message.save();
		Map<String,String> map = new HashMap<>();
		map.put("id", message.getMessageId());
		renderJson(map);
	}
	
	
	public void getMsg() {
		String id = getPara("id");
		Message message = service.findOne(id);
		renderJson(message);
	}
}
