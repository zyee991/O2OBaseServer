package com.o2o.message;

import java.io.IOException;
import java.util.Date;
import javax.websocket.Session;

import org.apache.commons.lang3.StringUtils;

import com.o2o.common.model.Message;
import com.o2o.websocket.WebSocketEndpoint;

public class MessageHandler {
	/**
	 * 发送消息
	 * @param message
	 */
	public static void echo(Message message) {
		if(message != null) {
			String receiver = message.getMessageReceiver();
			if(StringUtils.isNotBlank(receiver)) {
				echoSingle(message);
			}
			String receiverBatch = message.getMessageReceiverBatch();
			if(StringUtils.isNoneBlank(receiverBatch)) {
				echoBatch(message);
			}
			message.setMessageSendTime(new Date());
			message.save();
		}
	}
	
	
	
	
	
	private static void echoSingle(Message message) {
		String receiver = message.getMessageReceiver();
		Session session = WebSocketEndpoint.SESSION_MAP.get(receiver);
		if(session != null) {
			try {
				session.getBasicRemote().sendText(message.getMessageId());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void echoBatch(Message message) {
		String receiverBatch = message.getMessageReceiverBatch();
		String[] receiverArray = receiverBatch.split(",");
		for(String receiver:receiverArray) {
			Session session = WebSocketEndpoint.SESSION_MAP.get(receiver);
			if(session != null) {
				try {
					session.getBasicRemote().sendText(message.getMessageId());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
