package com.o2o.websocket;

import java.io.IOException;
import java.util.HashMap;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang3.StringUtils;

import com.o2o.bean.GlobalUser;

@ServerEndpoint(value = "/websocket")
public class WebSocketController {
	// 连接map
    private static final HashMap<String,Object> CONNECT = new HashMap<String,Object>();
    // 用户map
    private static final HashMap<String,Object> USERMAP = new HashMap<String,Object>();

    private Session session;
    
	@OnOpen
	public void onOpen(Session session) {
		String userId = session.getRequestParameterMap().get("id").get(0);
		if(StringUtils.isBlank(userId)) {
			try {
				session.getBasicRemote().sendText("error:缺失参数(id)，请在url后填写参数'?id=xxx'");
				session.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			GlobalUser globalUser = GlobalUser.createOne(userId);
			if(globalUser != null) {
				this.session = session;
				CONNECT.put(session.getId(), this);
				USERMAP.put(session.getId(), globalUser);
			}
		}
	}	
	
	@OnClose
	public void onClose(Session session) {
 
	}	
	
	@OnMessage
	public void onMessage(String requestJson, Session session) throws IOException {
		session.getBasicRemote().sendText(requestJson);
	}
}
