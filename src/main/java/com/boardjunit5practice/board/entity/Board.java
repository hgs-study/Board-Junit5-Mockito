package com.boardjunit5practice.board.entity;

import javax.persistence.*;

@Entity
@Table(name = "board")
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;
}
