package com.marketplace.resource;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mkt-bid")
public class Bid extends  Resource {

    private String bidId;

    private Double bidPrice;

    private String userId;

    private String projectId;

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
