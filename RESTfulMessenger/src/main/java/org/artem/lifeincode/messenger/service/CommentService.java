package org.artem.lifeincode.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.artem.lifeincode.messenger.database.DatabaseClass;
import org.artem.lifeincode.messenger.exception.DataNotFoundException;
import org.artem.lifeincode.messenger.model.Comment;
import org.artem.lifeincode.messenger.model.Message;

public class CommentService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public CommentService() {
		Map<Long, Comment> comments = messages.get(1L).getComments();
		comments.put(1L, new Comment(1L, "First Comment", "Admin"));
		comments.put(2L, new Comment(2L, "Second Comment", "User"));
	}
	
	public List<Comment> getComments(long messageId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId) {
		Message message = messages.get(messageId);
		if (message == null) {
			throw new DataNotFoundException("Message with id " + messageId + " not found!");
		}
		Map<Long, Comment> comments = message.getComments();
		Comment comment = comments.get(commentId);
		if (comment == null) {
			throw new DataNotFoundException("Comment with id " + commentId + " not found!");
		}
		return comment;
	}
	
	public Comment addComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if (comment.getId() <= 0) {
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment deleteComment(long messageId, long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}

}
