package com.marketplace.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MktErrorMessage implements Serializable {

    private MktErrorType mktErrorType;

    private String errorMessage;

    public MktErrorMessage() {

    }

    public MktErrorMessage(MktErrorType mktErrorType, Object... params) {
        this.mktErrorType = mktErrorType;
        this.errorMessage = String.format(mktErrorType.getDescription(), params);
    }


    public MktErrorType getMktErrorType() {
        return mktErrorType;
    }


    public String getErrorMessage() {
        return errorMessage;
    }


}
