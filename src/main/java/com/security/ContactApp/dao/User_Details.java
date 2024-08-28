package com.security.ContactApp.dao;

import jakarta.persistence.*;

@Entity
@Table(name = "user_details")
public class User_Details {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Column(name = "name")
    private String name;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "fb_id")
    private String fbId;

    @Column(name = "linkedin_id")
    private String linkedinId;

    @Column(name = "mob_no")
    private String mobNo;

//    //The @Lob annotation specifies that the database should store the property as Large Object
//    @Lob
//    @Column(name = "img",columnDefinition = "LONGBLOB")
//    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_registration_id")
    private User_Registration userRegistration;

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

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getLinkedinId() {
        return linkedinId;
    }

    public void setLinkedinId(String linkedinId) {
        this.linkedinId = linkedinId;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

//    public byte[] getImage() {
//        return image;
//    }
//
//    public void setImage(byte[] image) {
//        this.image = image;
//    }

    public User_Registration getUserRegistration() {
        return userRegistration;
    }

    public void setUserRegistration(User_Registration userRegistration) {
        this.userRegistration = userRegistration;
    }

    @Override
    public String toString() {
        return "User_Details{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", fbId='" + fbId + '\'' +
                ", linkedinId='" + linkedinId + '\'' +
                ", mobNo='" + mobNo + '\'' +
               // ", userRegistration=" + userRegistration +
                '}';
    }
}
