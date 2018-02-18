package com.marketplace.resource;

import com.marketplace.MktConstants;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "mkt-user")
public class User extends  Resource {

    private String userId;

    private String firstName;

    private String lastName;

    private String emailId;


    public User() {
    }

    public User(String firstName, String lastName, String emailId) {
        this.userId = MktConstants.USER_PREFIX + RandomStringUtils.randomAlphanumeric(6).toUpperCase();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.setCreatedTimeStamp(new Date());
        this.setLastModifiedTimeStamp(new Date());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
