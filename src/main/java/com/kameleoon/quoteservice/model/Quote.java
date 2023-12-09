package com.kameleoon.quoteservice.model;

import java.util.Date;

public class Quote {

    private String id;
    private String content;
    private Date modificationDate;
    private int rating;
    private String authorId;

    public Quote(String id, String content, Date modificationDate, int rating, String authorId) {
        this.id = id;
        this.content = content;
        this.modificationDate = modificationDate;
        this.rating = rating;
        this.authorId = authorId;
    }

    public Quote() {
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public int getRating() {
        return rating;
    }

    public String getAuthorId() {
        return authorId;
    }

    public Quote setId(String id) {
        this.id = id;
        return this;
    }

    public Quote setContent(String content) {
        this.content = content;
        return this;
    }

    public Quote setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
        return this;
    }

    public Quote setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public Quote setAuthorId(String authorId) {
        this.authorId = authorId;
        return this;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", modificationDate=" + modificationDate +
                ", rating=" + rating +
                ", authorId='" + authorId + '\'' +
                '}';
    }
}
