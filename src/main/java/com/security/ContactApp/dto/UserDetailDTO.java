package com.security.ContactApp.dto;

public class UserDetailDTO {
    private int id;
    private String name;
    private String emailId;
    private String facebookId;
    private String linkedInId;
    private String mobileNumber;

    // Constructor, getters, and setters
    public UserDetailDTO(int id, String name, String emailId, String facebookId, String linkedInId, String mobileNumber) {
        this.id=id;
        this.name = name;
        this.emailId = emailId;
        this.facebookId = facebookId;
        this.linkedInId = linkedInId;
        this.mobileNumber = mobileNumber;
    }

    // Getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getLinkedInId() {
        return linkedInId;
    }

    public void setLinkedInId(String linkedInId) {
        this.linkedInId = linkedInId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}

