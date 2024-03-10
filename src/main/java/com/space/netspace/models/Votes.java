package com.space.netspace.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_votes")
public class Votes {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quoteId;

    private int userId;


}
