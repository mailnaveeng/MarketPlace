package com.marketplace.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.marketplace.MktConstants;

import java.util.Date;

@JsonIgnoreProperties({ "id" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class Resource {

    private static Gson gson = new GsonBuilder().create();

    private String id;

    private String summary;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = MktConstants.DFL_DATE_FORMAT)
    private Date createdTimeStamp;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = MktConstants.DFL_DATE_FORMAT)
    private Date lastModifiedTimeStamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Date createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public Date getLastModifiedTimeStamp() {
        return lastModifiedTimeStamp;
    }

    public void setLastModifiedTimeStamp(Date lastModifiedTimeStamp) {
        this.lastModifiedTimeStamp = lastModifiedTimeStamp;
    }

    @Override
    public String toString() {
        return gson.toJson(this);
    }
}
