package com.space.netspace.repositories;

import com.space.netspace.models.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {
}
