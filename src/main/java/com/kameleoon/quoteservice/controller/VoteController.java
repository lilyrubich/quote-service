package com.kameleoon.quoteservice.controller;

import com.kameleoon.quoteservice.exception.ChangeExistingVoteException;
import com.kameleoon.quoteservice.model.Vote;
import com.kameleoon.quoteservice.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

@RestController
@RequestMapping("/quoteService/vote")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("/upvote")
    public Vote upvote(@RequestParam String quoteId, @RequestParam String userId) {
        try {
            return voteService.upvote(quoteId, userId);
        } catch (IllegalStateException | SQLException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (ChangeExistingVoteException e) {
            throw new ResponseStatusException(HttpStatus.ACCEPTED, e.getMessage());
        }
    }

    @GetMapping("/downvote")
    public Vote downvote(@RequestParam String quoteId, @RequestParam String userId) {
        try {
            return voteService.downvote(quoteId, userId);
        } catch (IllegalStateException | SQLException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (ChangeExistingVoteException e) {
            throw new ResponseStatusException(HttpStatus.ACCEPTED, e.getMessage());
        }
    }
}
