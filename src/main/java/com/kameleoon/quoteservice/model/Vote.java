package com.kameleoon.quoteservice.model;

public class Vote {

    private String id;
    private boolean isUpvote;
    private String userId;
    private String quoteId;


    public Vote(VoteBuilder voteBuilder) {
        this.id = voteBuilder.getId();
        this.isUpvote = voteBuilder.isUpvote();
        this.userId = voteBuilder.getUserId();
        this.quoteId = voteBuilder.getQuoteId();
    }

    public static VoteBuilder getBuilder() {
        return new VoteBuilder();
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

    @Override
    public String toString() {
        return "Vote{" +
                "id='" + id + '\'' +
                ", isUpvote=" + isUpvote +
                ", userId='" + userId + '\'' +
                ", quoteId='" + quoteId + '\'' +
                '}';
    }

    public static class VoteBuilder {
        private String id;
        private boolean isUpvote;
        private String userId;
        private String quoteId;

        public VoteBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public VoteBuilder setUpvote(boolean upvote) {
            isUpvote = upvote;
            return this;
        }

        public VoteBuilder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public VoteBuilder setQuoteId(String quoteId) {
            this.quoteId = quoteId;
            return this;
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

        public Vote build() {
            return new Vote(this);
        }
    }
}
