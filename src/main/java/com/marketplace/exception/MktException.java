package com.marketplace.exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

public abstract  class MktException extends  Exception {

    private final List<MktErrorMessage> errors;

    public MktException(String message, Throwable cause, List<MktErrorMessage> errors) {
        super(message, cause);
        this.errors = errors;
    }

    public List<MktErrorMessage> getErrors() {
        if(errors != null)
            return Collections.unmodifiableList(errors);

        return Collections.emptyList();
    }

    public abstract Response.Status getHTTPStatusCode();

    @Override
    public String toString(){
        Gson gson = new GsonBuilder().create();
        return gson.toJson(Collections.singletonMap(getMessage(), errors));
    }


}
