package com.kameleoon.quoteservice.service;

import com.kameleoon.quoteservice.dao.QuoteDAO;
import com.kameleoon.quoteservice.dao.VoteDAO;
import com.kameleoon.quoteservice.exception.ChangeExistingVoteException;
import com.kameleoon.quoteservice.model.Quote;
import com.kameleoon.quoteservice.model.Vote;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Date;
import java.time.Instant;
import java.util.UUID;

public class VoteServiceTest {

    private VoteDAO<Vote> voteDAO;
    private QuoteDAO<Quote> quoteDAO;
    private VoteService voteService;

    @Before
    public void init() {
        voteDAO = Mockito.mock(VoteDAO.class);
        quoteDAO = Mockito.mock(QuoteDAO.class);
        voteService = new VoteService(voteDAO, quoteDAO);
    }

    @Test
    public void should_createUpvote_whenVoteDoesntExistYet() throws NoSuchMethodException, ChangeExistingVoteException {
        String quoteId = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();
        Quote quote = new Quote(quoteId, "Some Content", Date.from(Instant.now()), 0, userId);

        Mockito.when(voteDAO.getVoteByUserIdAndQuoteId(quoteId, userId)).thenReturn(null);
        Mockito.when(quoteDAO.getQuoteById(quoteId)).thenReturn(quote);

        voteService.upvote(quoteId, userId);
        Mockito.verify(voteDAO, Mockito.atLeastOnce()).createVote(Mockito.any());
    }

    @Test
    public void should_createDownvote_whenVoteDoesntExistYet() throws ChangeExistingVoteException {
        String quoteId = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();
        Quote quote = new Quote(quoteId, "Some Content", Date.from(Instant.now()), 0, userId);

        Mockito.when(voteDAO.getVoteByUserIdAndQuoteId(quoteId, userId)).thenReturn(null);
        Mockito.when(quoteDAO.getQuoteById(quoteId)).thenReturn(quote);

        voteService.downvote(quoteId, userId);
        Mockito.verify(voteDAO, Mockito.atLeastOnce()).createVote(Mockito.any());
    }

    @Test(expected = ChangeExistingVoteException.class)
    public void should_removeVote_whenMakeADownvote_butUpvoteAlreadyExist() throws ChangeExistingVoteException {
        String quoteId = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();
        String voteId = UUID.randomUUID().toString();
        Quote quote = new Quote(quoteId, "Some Content", Date.from(Instant.now()), 0, userId);
        Vote vote = new Vote(voteId, true, userId, quoteId);

        Mockito.when(voteDAO.getVoteByUserIdAndQuoteId(quoteId, userId)).thenReturn(vote);
        Mockito.when(voteDAO.deleteVote(voteId)).thenReturn(true);
        Mockito.when(quoteDAO.getQuoteById(quoteId)).thenReturn(quote);

        voteService.downvote(quoteId, userId);
    }

    @Test(expected = ChangeExistingVoteException.class)
    public void should_removeVote_whenMakeAnUpvote_butDownvoteAlreadyExist() throws ChangeExistingVoteException {
        String quoteId = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();
        String voteId = UUID.randomUUID().toString();
        Quote quote = new Quote(quoteId, "Some Content", Date.from(Instant.now()), 0, userId);
        Vote vote = new Vote(voteId, false, userId, quoteId);

        Mockito.when(voteDAO.getVoteByUserIdAndQuoteId(quoteId, userId)).thenReturn(vote);
        Mockito.when(voteDAO.deleteVote(voteId)).thenReturn(true);
        Mockito.when(quoteDAO.getQuoteById(quoteId)).thenReturn(quote);

        voteService.upvote(quoteId, userId);
    }

    @Test(expected = IllegalStateException.class)
    public void should_returnIllegalStateException_whenMakeAnUpvote_butUpvoteAlreadyExist() throws ChangeExistingVoteException {
        String quoteId = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();
        String voteId = UUID.randomUUID().toString();
        Vote vote = new Vote(voteId, true, userId, quoteId);

        Mockito.when(voteDAO.getVoteByUserIdAndQuoteId(quoteId, userId)).thenReturn(vote);

        voteService.upvote(quoteId, userId);
    }

    @Test(expected = IllegalStateException.class)
    public void should_returnIllegalStateException_whenMakeADownvote_butDownvoteAlreadyExist() throws ChangeExistingVoteException {
        String quoteId = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();
        String voteId = UUID.randomUUID().toString();
        Vote vote = new Vote(voteId, false, userId, quoteId);

        Mockito.when(voteDAO.getVoteByUserIdAndQuoteId(quoteId, userId)).thenReturn(vote);

        voteService.downvote(quoteId, userId);
    }
}
