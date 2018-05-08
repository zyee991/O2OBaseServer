package com.o2o.message;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.o2o.common.model.Message;

public class MessageFactory {
	
	/**
	 * 创建消息
	 * @param messageName
	 * @param messageSender
	 * @param messageReceiver
	 * @param messageReceivers
	 * @param messageTableName
	 * @param messageContent
	 * @param messageDataId
	 * @return
	 */
	public static Message createMessage(String messageName,String messageSender,String messageReceiver,List<String> messageReceivers,String messageTableName,String messageContent,String messageDataId) {
		Message message = new Message();
		message.setMessageId(UUID.randomUUID().toString());
		message.setMessageName(messageName);
		message.setMessageSender(messageSender);
		message.setMessageReceiver(messageReceiver);
		message.setMessageDataId(messageDataId);
		message.setMessageTableName(messageTableName);
		message.setMessageContent(messageContent);
		message.setMessageSendTime(new Date());
		if(messageReceivers != null && messageReceivers.size() > 0) {
			String messageReceiverBatch = StringUtils.join(messageReceivers.toArray(), ",");
			message.setMessageReceiverBatch(messageReceiverBatch);
		}
		return message;
	}
}
