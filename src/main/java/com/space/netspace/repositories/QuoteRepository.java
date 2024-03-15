package com.space.netspace.repositories;

import com.space.netspace.models.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {


    List<Quote> findTop10ByOrderByVotesDesc();

    List<Quote> findTop10ByOrderByVotesAsc();
}
