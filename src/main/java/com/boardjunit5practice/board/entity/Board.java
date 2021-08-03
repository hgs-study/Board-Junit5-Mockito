package com.boardjunit5practice.board.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "board")
@Getter
@NoArgsConstructor
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(columnDefinition = "bigint default 0")
    private Long hit;

    @Builder
    public Board(String title, String content, Long hit) {
        this.title = title;
        this.content = content;
        this.hit = hit;
    }
}
