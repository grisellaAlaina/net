package com.space.netspace.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_votes")
public class Vote {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "quote_id")
    private int quoteId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "vote")
    private boolean vote;

    public Vote() {
    }

    public Vote(int quoteId, int userId, boolean vote) {
        this.quoteId = quoteId;
        this.userId = userId;
        this.vote = vote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }
}
