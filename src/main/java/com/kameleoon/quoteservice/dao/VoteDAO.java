package com.kameleoon.quoteservice.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface VoteDAO<T> {

    void createVote(T t);

    boolean deleteVote(String id);

    void updateVote(T t);

    T getVoteByUserIdAndQuoteId(String quoteId, String userId);
}
