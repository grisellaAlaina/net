package com.space.netspace.repositories;

import com.space.netspace.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
    Vote findByUserIdAndQuoteId(int userId, int quoteId);
}
