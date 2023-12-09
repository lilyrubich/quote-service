package com.kameleoon.quoteservice.model;

public class Vote {

    private String id;
    private boolean isUpvote;
    private String userId;
    private String quoteId;

    public Vote(String id, boolean isUpvote, String user_id, String quoteId) {
        this.id = id;
        this.isUpvote = isUpvote;
        this.userId = user_id;
        this.quoteId = quoteId;
    }

    public Vote() {

    }

    public String getId() {
        return id;
    }

    public boolean isUpvote() {
        return isUpvote;
    }

    public String getUserId() {
        return userId;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public Vote setId(String id) {
        this.id = id;
        return this;
    }

    public Vote setUpvote(boolean upvote) {
        isUpvote = upvote;
        return this;
    }

    public Vote setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Vote setQuoteId(String quoteId) {
        this.quoteId = quoteId;
        return this;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id='" + id + '\'' +
                ", isUpvote=" + isUpvote +
                ", userId='" + userId + '\'' +
                ", quoteId='" + quoteId + '\'' +
                '}';
    }
}
