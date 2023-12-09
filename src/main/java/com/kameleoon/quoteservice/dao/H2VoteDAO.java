package com.kameleoon.quoteservice.dao;

import com.kameleoon.quoteservice.model.Vote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.PreparedStatement;
import java.util.Map;

public class H2VoteDAO implements VoteDAO<Vote> {

    private static final Logger log = LoggerFactory.getLogger(H2VoteDAO.class);
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public H2VoteDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    RowMapper<Vote> voteRowMapper = (rs, rowNum) -> {
        Vote vote = new Vote();
        vote.setId(rs.getString("id"));
        vote.setUpvote(rs.getBoolean("is_upvote"));
        vote.setUserId(rs.getString("user_id"));
        vote.setQuoteId(rs.getString("quote_id"));
        return vote;
    };

    @Override
    public void createVote(Vote vote) {
        String sql = "insert into votes (id, is_upvote, user_id, quote_id) values (?,?,?,?)";

        int insert = jdbcTemplate.update(
                connection -> {
                    PreparedStatement prepareStatement = connection.prepareStatement(sql);
                    prepareStatement.setString(1, vote.getId());
                    prepareStatement.setBoolean(2, vote.isUpvote());
                    prepareStatement.setString(3, vote.getUserId());
                    prepareStatement.setString(4, vote.getQuoteId());
                    return prepareStatement;
                });

        if (insert == 1) {
            log.info("New vote has been created: " + vote.toString());
        } else log.info("Something went wrong during insert user");

    }

    @Override
    public boolean deleteVote(String id) {
        String sql = "delete from votes where id=?";
        if (jdbcTemplate.update(sql, id) >= 1) {
            log.info("Vote with id = " + id + "has been removed");
            return true;
        } else {
            log.info("Failed to delete vote with id = " + id);
            return false;
        }
    }

    @Override
    public void updateVote(Vote vote) {
        String sql = "update votes set is_upvote=:is_upvote, user_id=:user_id, quote_id=:quote_id where id=:id";
        int update = namedParameterJdbcTemplate.update(sql, Map.of(
                "is_upvote", vote.isUpvote(),
                "user_id", vote.getUserId(),
                "quote_id", vote.getQuoteId()));

        if (update == 1) {
            log.info("Vote id = " + vote.getId() + " has been updated: " + vote);
        } else log.info("Something went wrong during update quote");
    }

    @Override
    public Vote getVoteByUserIdAndQuoteId(String quoteId, String userId) {
        String sql = "select * from votes where quote_id=:quote_id and user_id=:user_id";
        try {
            return namedParameterJdbcTemplate.queryForObject(sql, Map.of(
                            "quote_id", quoteId,
                            "user_id", userId),
                    voteRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
