package com.o2o.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket")
public class WebSocketController {
	
	Map<String ,Session> map = new HashMap<>();
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println(session.getRequestParameterMap().get("id"));
		System.out.println(session.getRequestParameterMap().get("type"));
	}	
	
	@OnClose
	public void onClose(Session session) {
 
	}	
	
	@OnMessage
	public void onMessage(String requestJson, Session session) throws IOException {
		session.getBasicRemote().sendText(requestJson);
	}
}
