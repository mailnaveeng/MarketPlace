package com.marketplace.exception;

import javax.ws.rs.core.Response;
import java.util.List;

public class ResourceNotFoundException extends  MktException  {

    public ResourceNotFoundException(String message, Throwable cause, List<MktErrorMessage> errors) {
        super(message, cause, errors);
    }

    @Override
    public Response.Status getHTTPStatusCode() {
        return Response.Status.NOT_FOUND;
    }
}
