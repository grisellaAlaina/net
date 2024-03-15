package com.space.netspace.services;

import com.space.netspace.models.Quote;
import com.space.netspace.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class QuoteService {
    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }
    public List<Quote> findAll() {
        return quoteRepository.findAll();
    }

    @Transactional
    public void save(Quote quote) {
        createTimeQuote(quote);
        quoteRepository.save(quote);
    }

    @Transactional
    public void update(Quote quote) {
        quote.setUpdatedAt(LocalDateTime.now());
        quoteRepository.save(quote);
    }

    @Transactional
    public void delete(int id) {
        quoteRepository.deleteById(id);
    }

    public Quote findOne(int id) {
        Optional<Quote> foundQuote = quoteRepository.findById(id);
        return foundQuote.orElse(null);
    }

    public void createTimeQuote(Quote quote) {
        quote.setCreatedAt(LocalDateTime.now());
        quote.setUpdatedAt(LocalDateTime.now());
    }


    @Transactional
    public void changeVote(int quoteId, int voteValue) {
        Quote quote = findOne(quoteId);
        quote.setVotes(quote.getVotes() + voteValue);
        quoteRepository.save(quote);
    }

    public List<Quote> bestOrWorst(boolean top) {
        return top ? quoteRepository.findTop10ByOrderByVotesDesc() :
                quoteRepository.findTop10ByOrderByVotesAsc();
    }
}
