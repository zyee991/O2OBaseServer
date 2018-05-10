package com.o2o.service;

import java.util.List;

import com.o2o.common.model.Message;

public class MessageService {
	
	private static Message dao = new Message().dao();

	public List<Message> msgTableData() {
		String sql = "SELECT * FROM tb_base_message";
		return dao.find(sql);
	}

	public List<Message> messageList(String userId1, String userId2) {
		String sql = "SELECT * FROM tb_base_message WHERE (message_sender = ? and message_receiver = ?)"
				+ " OR (message_sender = ? and message_receiver = ?) OR message_receiver is null ORDER BY message_send_time";
		List<Message> messageList = dao.find(sql,userId1,userId2,userId2,userId1);
		return messageList;
	}

	public Message findOne(String id) {
		return dao.findById(id);
	}

}
