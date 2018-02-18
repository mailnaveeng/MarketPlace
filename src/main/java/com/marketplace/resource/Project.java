package com.marketplace.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marketplace.MktConstants;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "mkt-project")
public class Project extends  Resource {

    private String projectId;

    private String name;

    private String requirements;

    private Double budget;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = MktConstants.DFL_DATE_FORMAT)
    private Date bidOpenDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = MktConstants.DFL_DATE_FORMAT)
    private Date bidCloseDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = MktConstants.DFL_DATE_FORMAT)
    private Date expectedDelivery;

    private String userId;

    @Transient
    private Bid bestBid;


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Date getBidOpenDate() {
        return bidOpenDate;
    }

    public void setBidOpenDate(Date bidOpenDate) {
        this.bidOpenDate = bidOpenDate;
    }

    public Date getBidCloseDate() {
        return bidCloseDate;
    }

    public void setBidCloseDate(Date bidCloseDate) {
        this.bidCloseDate = bidCloseDate;
    }

    public Date getExpectedDelivery() {
        return expectedDelivery;
    }

    public void setExpectedDelivery(Date expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Bid getBestBid() {
        return bestBid;
    }

    public void setBestBid(Bid bestBid) {
        this.bestBid = bestBid;
    }
}
