package com.marketplace.exception;

public enum MktErrorType {

    RESOURCE_NOT_FOUND("Unable to find resource with id: %s."),
    INVALID_ENTITY("Invalid Entity : %s"),
    INTERNAL_SERVER_ERROR("Internal Server Error. Contact Administrator");

    private String description;

    MktErrorType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
