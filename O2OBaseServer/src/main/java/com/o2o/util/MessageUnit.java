package com.o2o.util;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import org.apache.commons.lang3.StringUtils;

import com.o2o.common.model.Message;
import com.o2o.websocket.WebSocketEndpoint;

public class MessageUnit {
	private static final Message dao = new Message().dao();
	
	private static final int ORDER = 20001;
	private static final int SERVICE_ORDER = 20002;
	
	/**
	 * 创建消息
	 * @param messageName
	 * @param messageSender
	 * @param messageReceiver
	 * @param messageReceivers
	 * @param messageType
	 * @param messageContent
	 * @param messageDataId
	 * @return
	 */
	public static Message createMessage(String messageName,String messageSender,String messageReceiver,List<String> messageReceivers,int messageType,String messageContent,String messageDataId) {
		Message message = new Message();
		message.setMessageId(UUID.randomUUID().toString());
		message.setMessageName(messageName);
		message.setMessageSender(messageSender);
		message.setMessageReceiver(messageReceiver);
		message.setMessageSendTime(new Date());
		message.setMessageDataId(messageDataId);
		if(messageReceivers != null && messageReceivers.size() > 0) {
			String messageReceiverBatch = StringUtils.join(messageReceivers.toArray(), ",");
			message.setMessageReceiverBatch(messageReceiverBatch);
		}
		return message;
	}
	
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
		}
	}
	
	private static void echoSingle(Message message) {
		String receiver = message.getMessageReceiver();
		Session session = WebSocketEndpoint.SESSION_MAP.get(receiver);
		if(session != null) {
			try {
				session.getBasicRemote().sendObject(message);
			} catch (IOException | EncodeException e) {
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
					session.getBasicRemote().sendObject(message);
				} catch (IOException | EncodeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
