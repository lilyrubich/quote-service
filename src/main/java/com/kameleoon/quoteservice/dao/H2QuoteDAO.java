package com.kameleoon.quoteservice.dao;

import com.kameleoon.quoteservice.model.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class H2QuoteDAO implements QuoteDAO<Quote> {

    private static final Logger log = LoggerFactory.getLogger(H2QuoteDAO.class);
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public H2QuoteDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    RowMapper<Quote> quoteRowMapper = (rs, rowNum) -> {
        Quote quote = new Quote();
        quote.setId(rs.getString("id"));
        quote.setContent(rs.getString("content"));
        quote.setModificationDate(rs.getDate("modification_date"));
        quote.setRating(rs.getInt("rating"));
        quote.setAuthorId(rs.getString("author_id"));
        return quote;
    };

    RowMapper<String> stringRowMapper = (rs, rowNum) -> rs.getString("id");

    @Override
    public List<Quote> getAllQuotes() {
        String sql = "select * from quotes";
        return jdbcTemplate.query(sql, quoteRowMapper);
    }

    @Override
    public void createQuote(Quote quote) throws SQLException {
        String sql = "insert into quotes (id, content, modification_date, author_id) values (?,?,?,?)";

        int insert = jdbcTemplate.update(
                connection -> {
                    PreparedStatement prepareStatement = connection.prepareStatement(sql);
                    prepareStatement.setString(1, quote.getId());
                    prepareStatement.setString(2, quote.getContent());
                    prepareStatement.setDate(3, (Date) quote.getModificationDate());
                    prepareStatement.setString(4, quote.getAuthorId());
                    return prepareStatement;
                });

        if (insert == 1) {
            log.info("New quote created: " + quote.toString());
        } else {
            log.info("Something went wrong during insert quote");
            throw new SQLException("Failed to create new quote. The reason is incorrect input data");
        }
    }

    @Override
    public void updateQuote(Quote quote) throws SQLException {
        String sql = "update quotes set content=:content, modification_date=:modification_date, rating=:rating, author_id=:author_id where id=:id";
        int update = namedParameterJdbcTemplate.update(sql, Map.of(
                "content", quote.getContent(),
                "modification_date", quote.getModificationDate(),
                "rating", quote.getRating(),
                "author_id", quote.getAuthorId(),
                "id", quote.getId()));

        if (update == 1) {
            log.info("Quote id = " + quote.getId() + " has been updated: " + quote);
        } else {
            log.info("Something went wrong during update quote");
            throw new SQLException("Failed to create new quote. The reason is incorrect input data");
        }
    }

    @Override
    public boolean deleteQuote(String id) throws SQLException {
        String sql = "delete from quotes where id=?";
        if (jdbcTemplate.update(sql, id) >= 1) {
            log.info("Quote with id = " + id + " has been removed");
            return true;
        } else {
            log.info("Failed to delete quote with id = " + id);
            throw new SQLException("Failed to delete quote. Possible reason is non-existent id");
        }
    }

    @Override
    public List<Quote> getTop10Quotes() {
        String sql = "select * from quotes order by rating desc limit 10";
        return jdbcTemplate.query(sql, quoteRowMapper);
    }

    @Override
    public List<Quote> getWorst10Quotes() {
        String sql = "select * from quotes order by rating limit 10";
        return jdbcTemplate.query(sql, quoteRowMapper);
    }

    @Override
    public Quote getQuoteById(String id) {
        String sql = "select * from quotes where id=:id";
        return namedParameterJdbcTemplate.query(sql, Map.of("id", id), quoteRowMapper).get(0);
    }

    @Override
    public List<String> getQuoteIds() {
        String sql = "select id from quotes";
        return jdbcTemplate.query(sql, stringRowMapper);
    }
}
