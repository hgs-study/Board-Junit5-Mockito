package com.boardjunit5practice.board.application;

import com.boardjunit5practice.board.entity.Board;
import com.boardjunit5practice.board.form.BoardForm.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.persistence.EntityManager;

import java.util.Optional;

import static com.boardjunit5practice.board.entity.QBoard.board;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    EntityManager em;

    JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    void setUp(){
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    @DisplayName("글쓰기")
    @Test
    void register(){
        //given
        final Board board = new Board("hello world","hgstudy");
        boardRepository.save(board);

        //when
        final Board savedBoard = boardRepository.findById(1L).get();

        //then
        assertEquals(savedBoard.getTitle(), board.getTitle());
    }

    @DisplayName("조회수 증가 확인")
    @Test
    void hit(){
        //given
        final Board saveBoard = new Board("hello world","hgstudy",0L);
        boardRepository.save(saveBoard);

        //when
        jpaQueryFactory
                .update(board)
                .where(board.id.eq(1L))
                .set(board.hit, board.hit.add(1L))
                .execute();
        em.clear();
        final Board findBoard = boardRepository.findById(1L).get();

        //then
        assertAll(
                () -> assertNotNull(findBoard),
                () -> assertTrue(findBoard.getHit() > 0L),
                () -> assertEquals(findBoard.getHit() , 1L)
        );
    }

    @DisplayName("수정")
    @Test
    void modify(){
        //given
        final Board saveBoard = new Board("hello world","hgstudy",0L);
        final Board modifyBoard = new Board("modifyTitle","modifyContent",1L);
        boardRepository.save(saveBoard);

        //when
        saveBoard.update(modifyBoard);
        em.flush();
        em.clear();
        final Board findBoard = boardRepository.findById(1L).get();

        //then
        assertAll(
                () -> assertNotNull(findBoard),
                () -> assertTrue(findBoard.getHit().equals(1L)),
                () -> assertEquals(findBoard.getTitle() , "modifyTitle")
        );
    }

    @DisplayName("삭제")
    @Test
    void delete(){
        //given
        final Board saveBoard = new Board("hello world","hgstudy",0L);
        boardRepository.save(saveBoard);

        //when
        boardRepository.delete(saveBoard);

        //then
        assertThrows(IllegalArgumentException.class, () ->
                boardRepository.findById(saveBoard.getId())
                               .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")));
    }


}
