package com.kameleoon.quoteservice.service;

import com.kameleoon.quoteservice.dao.QuoteDAO;
import com.kameleoon.quoteservice.model.Quote;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.random.RandomGenerator;

@Service
public class QuoteService {

    private final QuoteDAO<Quote> quoteDAO;

    public QuoteService(QuoteDAO quoteDAO) {
        this.quoteDAO = quoteDAO;
    }


    public List<Quote> getQuotes() {
        return quoteDAO.getAllQuotes();
    }

    public Quote saveQuote(Quote quote) throws SQLException {
        //generate uuid and date
        UUID uuid = UUID.randomUUID();
        quote.setId(uuid.toString());
        Date modificationDate = new Date(System.currentTimeMillis());
        quote.setModificationDate(modificationDate);

        quoteDAO.createQuote(quote);
        return quote;
    }

    public Quote updateQuoteWithNewModificationDate(Quote quote) throws SQLException {
        //generate modification date
        Date modificationDate = new Date(System.currentTimeMillis());
        quote.setModificationDate(modificationDate);

        quoteDAO.updateQuote(quote);
        return quote;
    }

    public Quote updateQuoteWithoutNewModificationDate(Quote quote) throws SQLException {
        quoteDAO.updateQuote(quote);
        return quote;
    }

    public String removeQuote(String id) throws SQLException {
        quoteDAO.deleteQuote(id);
        return "Quote id = " + id + "has been removed";
    }

    public List<Quote> getTop10Quotes() {
        return quoteDAO.getTop10Quotes();
    }

    public List<Quote> getWorst10Quotes() {
        return quoteDAO.getWorst10Quotes();
    }

    public Quote getRandomQuote() {
        List<String> quoteIds = quoteDAO.getQuoteIds();
        int randomIndex = RandomGenerator.getDefault().nextInt(quoteIds.size());

        return quoteDAO.getQuoteById(quoteIds.get(randomIndex));
    }
}
