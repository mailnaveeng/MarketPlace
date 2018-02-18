package com.marketplace.jaxrs;

import com.marketplace.exception.MktErrorMessage;
import com.marketplace.exception.MktErrorType;
import com.marketplace.exception.MktException;
import com.marketplace.service.MktService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Collections;

@Provider
public class MktExceptionMapper implements ExceptionMapper<Throwable> {

    private static Logger logger = LoggerFactory.getLogger(MktService.class);

    @Override
    public Response toResponse(Throwable ex) {
        logger.error(ex.getMessage(), ex);
        if(ex instanceof MktException){

            MktException currEx = (MktException)ex;
            return Response.status(currEx.getHTTPStatusCode()).entity(
                    Collections.singletonMap("errors", currEx.getErrors())).build();

        }else{
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity(Collections.singletonMap("errors",
                            Collections.singletonList(new MktErrorMessage(MktErrorType.INTERNAL_SERVER_ERROR,
                    null)))).build();
        }
    }
}
