package com.kameleoon.quoteservice.dao;

import com.kameleoon.quoteservice.model.Quote;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface QuoteDAO<T> {

    List<T> getAllQuotes();

    void createQuote(T t) throws SQLException;

    void updateQuote(T t) throws SQLException;

    boolean deleteQuote(String id) throws SQLException;

    List<T> getTop10Quotes();

    List<Quote> getWorst10Quotes();

    T getQuoteById(String id);

    List<String> getQuoteIds();
}
