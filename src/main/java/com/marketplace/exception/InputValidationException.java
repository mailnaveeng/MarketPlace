package com.marketplace.exception;

import javax.ws.rs.core.Response;
import java.util.List;

public class InputValidationException extends  MktException {

    public InputValidationException(String message, Throwable cause, List<MktErrorMessage> errors) {
        super(message, cause, errors);
    }

    @Override
    public Response.Status getHTTPStatusCode() {
        return Response.Status.BAD_REQUEST;
    }


}
