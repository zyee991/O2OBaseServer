package com.o2o.message;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.o2o.common.model.Message;

public class MessageFactory {
	private static final int ORDER = 20001;
	private static final int SERVICE_ORDER = 20002;
	
	private static final Message dao = new Message().dao();

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
		message.setMessageDataId(messageDataId);
		if(messageReceivers != null && messageReceivers.size() > 0) {
			String messageReceiverBatch = StringUtils.join(messageReceivers.toArray(), ",");
			message.setMessageReceiverBatch(messageReceiverBatch);
		}
		return message;
	}
	
	public static Message findMessage(String id) {
		Message message = dao.findById(id);
		return message;
	}
	
}
