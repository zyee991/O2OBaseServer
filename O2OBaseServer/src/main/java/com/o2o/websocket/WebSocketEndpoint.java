package com.o2o.websocket;

import java.io.IOException;
import java.util.HashMap;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.o2o.bean.GlobalUser;

@ServerEndpoint(value = "/wsmsg")
public class WebSocketEndpoint {
    // 用户map
    public static final HashMap<String,GlobalUser> USER_MAP = new HashMap<String,GlobalUser>();
    // session Map
    public static final HashMap<String,Session> SESSION_MAP = new HashMap<String,Session>();
    
    private static Logger log = LoggerFactory.getLogger(WebSocketEndpoint.class);
    
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
			GlobalUser globalUser = GlobalUser.findOne(userId);
			if(globalUser != null) {
				USER_MAP.put(session.getId(), globalUser);
				SESSION_MAP.put(userId,session);
				log.info("WebSocket已连接------" + globalUser);
			}
		}
	}	
	
	@OnClose
	public void onClose(Session session) {
		String userId = USER_MAP.get(session.getId()).getId();
		USER_MAP.remove(session.getId());
		SESSION_MAP.remove(userId);
	}	
	
	@OnMessage
	public void onMessage(String requestJson, Session session) throws IOException {
		session.getBasicRemote().sendText(requestJson);
	}
}
