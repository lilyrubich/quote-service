package com.kameleoon.quoteservice.controller;

import com.kameleoon.quoteservice.model.Quote;
import com.kameleoon.quoteservice.service.QuoteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/quoteService/quote")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping("/add")
    public Quote addQuote(@RequestBody Quote quote) {
        try {
            return quoteService.saveQuote(quote);
        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/getQuotes")
    public List<Quote> getQuotes() {
        return quoteService.getQuotes();
    }

    @PostMapping("/update")
    public Quote updateQuote(@RequestBody Quote quote) {
        try {
            return quoteService.updateQuoteWithNewModificationDate(quote);
        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public String removeQuote(@RequestParam String id) {
        try {
            return quoteService.removeQuote(id);
        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/getTop10")
    public List<Quote> getTop10Quotes() {
        return quoteService.getTop10Quotes();
    }

    @GetMapping("/getWorst10")
    public List<Quote> getWorst10Quotes() {
        return quoteService.getWorst10Quotes();
    }

    @GetMapping("/getRandomQuote")
    public Quote getRandomQuote() {
        return quoteService.getRandomQuote();
    }
}
