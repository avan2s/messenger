package org.andy.javabrains.messenger.resources;

import org.andy.javabrains.messenger.model.Comment;
import org.andy.javabrains.messenger.service.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Andy on 30.01.2017.
 * Sub Resource
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

    private CommentService commentService = new CommentService();

    @GET
    public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
        return this.commentService.getAllComments(messageId);
    }

    @GET
    @Path("/{commentId}")
    public Comment getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
        return this.commentService.getComment(messageId, commentId);
    }

    @POST
    public Comment addComment(@PathParam("messageId") long messageId, Comment comment) {
        return this.commentService.addComment(messageId, comment);
    }

    @PUT
    @Path("/{commentId}")
    public Comment addComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId, Comment comment) {
        comment.setId(commentId);
        return this.commentService.updateComment(messageId, comment);
    }

    @DELETE
    @Path("/{commentId}")
    public Comment addComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
        return this.commentService.removeComment(messageId, commentId);
    }

}
