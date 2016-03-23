package org.artem.lifeincode.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.artem.lifeincode.messenger.database.DatabaseClass;
import org.artem.lifeincode.messenger.exception.DataNotFoundException;
import org.artem.lifeincode.messenger.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public MessageService() {
		messages.put(1L, new Message(1L, "Hello World!", "artem"));
		messages.put(2L, new Message(2L, "Hello Jersey!", "artem"));
	}
	
	public List<Message> getMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getMessagesForYear(int year) {
		List<Message> messageForYear = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		for (Message message : messages.values()) {
			calendar.setTime(message.getCreated());
			if (calendar.get(Calendar.YEAR) == year) {
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}
	
	public List<Message> getMessagesPaginated(int start, int size) {
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if ((start + size) > list.size()) {
			return new ArrayList<Message>();
		}
		return list.subList(start, start + size);
	}
	
	public Message getMessage(long messageId) {
		Message message = messages.get(messageId);
		if (message == null) {
			throw new DataNotFoundException("Message with id " + messageId + " not found!");
		}
		return message;
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message deleteMessage(long messageId) {
		return messages.remove(messageId);
	}
}
