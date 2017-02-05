package org.andy.javabrains.messenger.resources;

import org.andy.javabrains.messenger.model.Message;
import org.andy.javabrains.messenger.resources.beans.MessageFilterBean;
import org.andy.javabrains.messenger.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    private MessageService messageService = new MessageService();

    public MessageResource() {

    }

    @GET
    public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
        if (filterBean.getYear() > 0) {
            return this.messageService.getAllMessagesForYear(filterBean.getYear());
        }
        if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
            return this.messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
        }
        return this.messageService.getAllMessages();
    }

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
        Message message = this.messageService.getMessage(id);
        message.addLink(this.getUriForSelf(uriInfo, message), "self");
        message.addLink(this.getUriForProfile(uriInfo, message), "profile");
        message.addLink(this.getUriForComments(uriInfo, message), "comments");
        return message;
    }


    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
        Message newMessage = this.messageService.addMessage(message);
        // add status
        //        return Response.status(Response.Status.CREATED)
        //                .entity(newMessage)
        //                .build();
        String newId = String.valueOf(newMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();

        // send locationValue in the Header with created Method
        return Response.created(uri)
                .entity(newMessage)
                .build();
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") long id, Message message) {
        message.setId(id);
        return this.messageService.updateMessage(message);
    }

    @DELETE
    @Path("{messageId}")
    public Message deleteMessage(@PathParam("messageId") long id) {
        return this.messageService.removeMessage(id);
    }

    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {
        return new CommentResource();
    }

    private String getUriForSelf(UriInfo uriInfo, Message message) {
        return uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(Long.toString(message.getId()))
                .build()
                .toString();
    }

    private String getUriForProfile(UriInfo uriInfo, Message message) {
        return uriInfo.getBaseUriBuilder()
                .path(ProfileResource.class)
                .path(message.getAuthor())
                .build()
                .toString();
    }

    private String getUriForComments(UriInfo uriInfo, Message message) {
        return uriInfo.getBaseUriBuilder()
                .path(MessageResource.class, "getCommentResource")
                .resolveTemplate("messageId", message.getId())
                .toString();
    }
}
