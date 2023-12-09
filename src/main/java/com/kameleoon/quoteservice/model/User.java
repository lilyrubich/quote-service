package com.kameleoon.quoteservice.model;


import java.util.Date;

public class User {

    private String id;
    private String login;
    private String email;
    private String password;
    private Date creationDate;

    public User(String id, String login, String email, String password, Date creationDate) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
