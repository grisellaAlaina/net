package com.space.netspace.services;


import com.space.netspace.models.Quote;
import com.space.netspace.models.Vote;
import com.space.netspace.repositories.QuoteRepository;
import com.space.netspace.repositories.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class VoteService {
    private final VoteRepository voteRepository;
    private final QuoteService quoteService;

    public VoteService(VoteRepository voteRepository, QuoteService quoteService) {
        this.voteRepository = voteRepository;
        this.quoteService = quoteService;
    }

    public Vote findVote(int userId, int quoteId) {
        return voteRepository.findByUserIdAndQuoteId(userId, quoteId);
    }


    @Transactional
    public void makeVote(int userId, int quoteId, boolean grade) {
        if (findVote(userId, quoteId) == null) {
            createVote(userId, quoteId, grade);
            quoteService.changeVote(quoteId, grade, true);
        }
        else {
            Vote vote = findVote(userId, quoteId);
            if(vote.isVote() == grade) {
                voteRepository.delete(vote);
                quoteService.changeVote(quoteId, grade, false);
            }
            else {
                vote.setVote(grade);
                voteRepository.save(vote);
            }
        }
    }

    @Transactional
    public void createVote(int userId, int quoteId, boolean grade) {
        voteRepository.save(new Vote(quoteId, userId, grade));
    }
}
