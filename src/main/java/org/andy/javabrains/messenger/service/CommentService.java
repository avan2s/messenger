package org.andy.javabrains.messenger.service;

import org.andy.javabrains.messenger.database.DatabaseClass;
import org.andy.javabrains.messenger.model.Comment;
import org.andy.javabrains.messenger.model.ErrorMessage;
import org.andy.javabrains.messenger.model.Message;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Andy on 30.01.2017.
 */
public class CommentService {
    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public List<Comment> getAllComments(long messageId) {
        Map<Long, Comment> comments = this.messages.get(messageId).getComments();
        return new ArrayList<Comment>(comments.values());
    }

    public Comment getComment(long messageId, long commentId) {
        Message message = this.messages.get(messageId);


        // No business Code
        ErrorMessage errorMessage = new ErrorMessage("Not Found", 404, "http://www.google.de");
        Response response = Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();

        if (message == null) {
            throw new WebApplicationException(response);
        }

        Map<Long, Comment> comments = message.getComments();
        Comment comment = comments.get(commentId);

        if (comment == null) {
            throw new NotFoundException(response);
        }
        return comment;
    }

    public Comment addComment(long messageId, Comment comment) {
        Map<Long, Comment> comments = this.messages.get(messageId).getComments();
        int id = this.messages.size() + 1;
        comment.setId(id);
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment updateComment(long messageId, Comment comment) {
        Map<Long, Comment> comments = this.messages.get(messageId).getComments();
        if (comment.getId() <= 0) {
            return null;
        }
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment removeComment(long messageId, long commentId) {
        return this.messages.get(messageId).getComments().remove(commentId);
    }

}
