package com.kameleoon.quoteservice.service;

import com.kameleoon.quoteservice.exception.ChangeExistingVoteException;
import com.kameleoon.quoteservice.dao.QuoteDAO;
import com.kameleoon.quoteservice.dao.VoteDAO;
import com.kameleoon.quoteservice.model.Quote;
import com.kameleoon.quoteservice.model.Vote;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.UUID;

@Service
public class VoteService {

    private final VoteDAO<Vote> voteDAO;
    private final QuoteDAO<Quote> quoteDAO;

    public VoteService(VoteDAO voteDAO, QuoteDAO quoteDAO) {
        this.voteDAO = voteDAO;
        this.quoteDAO = quoteDAO;
    }

    public Vote upvote(String quoteId, String userId) throws ChangeExistingVoteException, IllegalStateException, SQLException {

        Vote existingVote = voteDAO.getVoteByUserIdAndQuoteId(quoteId, userId);

        if (existingVote != null) {
            if (!existingVote.isUpvote()) {
                voteDAO.deleteVote(existingVote.getId());
                incrementRatingOfQuote(quoteId);
                throw new ChangeExistingVoteException("Existing vote has been removed: " + existingVote.getId());
            } else {
                throw new IllegalStateException("It's impossible to make more than 1 upvote per quote");
            }
        }

        //if voting doesn't exist yet, then create it
        Vote newVote = createVote(quoteId, userId, true);
        incrementRatingOfQuote(quoteId);

        return newVote;
    }

    public Vote downvote(String quoteId, String userId) throws ChangeExistingVoteException, IllegalStateException, SQLException {

        Vote existingVote = voteDAO.getVoteByUserIdAndQuoteId(quoteId, userId);

        if (existingVote != null) {
            if (existingVote.isUpvote()) {
                voteDAO.deleteVote(existingVote.getId());
                decrementRatingOfQuote(quoteId);
                throw new ChangeExistingVoteException("Existing downvote has been removed: " + existingVote.getId());
            } else {
                throw new IllegalStateException("It's impossible to make more than 1 downvote per quote");
            }
        }

        //if voting doesn't exist yet, then create it
        Vote newVote = createVote(quoteId, userId, false);
        decrementRatingOfQuote(quoteId);

        return newVote;
    }

    protected Vote createVote(String quoteId, String userId, boolean isUpvote) {
        Vote vote = new Vote();
        UUID uuid = UUID.randomUUID();
        vote.setId(uuid.toString());
        vote.setUpvote(isUpvote);
        vote.setQuoteId(quoteId);
        vote.setUserId(userId);

        voteDAO.createVote(vote);
        return vote;
    }

    protected int incrementRatingOfQuote(String quoteId) throws SQLException {
        Quote quote = quoteDAO.getQuoteById(quoteId);
        quote.setRating(quote.getRating() + 1);
        quoteDAO.updateQuote(quote);
        return quote.getRating();
    }

    protected int decrementRatingOfQuote(String quoteId) throws SQLException {
        Quote quote = quoteDAO.getQuoteById(quoteId);
        quote.setRating(quote.getRating() - 1);
        quoteDAO.updateQuote(quote);
        return quote.getRating();
    }
}
