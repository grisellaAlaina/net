package com.space.netspace.controllers;

import com.space.netspace.models.Vote;
import com.space.netspace.services.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/like")
    public ResponseEntity<HttpStatus> like(@RequestBody Vote vote) {
        voteService.makeVote(vote.getUserId(), vote.getQuoteId(), vote.isVote());

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
