package com.security.ContactApp.dao;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user_register")
public class User_Registration {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


    @OneToMany(mappedBy = "userRegistration", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User_Details> userDetailsList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<User_Details> getUserDetailsList() {
        return userDetailsList;
    }

    public void setUserDetailsList(List<User_Details> userDetailsList) {
        this.userDetailsList = userDetailsList;
    }


    @Override
    public String toString() {
        return "User_Registration{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                //", userDetailsList=" + userDetailsList +
                '}';
    }
}
