package org.andy.javabrains.messenger.exception;

import org.andy.javabrains.messenger.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable exception) {
        String documentation = "http://www.google.de";
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 500, documentation);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .build();
    }
}
