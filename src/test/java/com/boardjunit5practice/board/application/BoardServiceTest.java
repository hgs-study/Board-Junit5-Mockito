package com.boardjunit5practice.board.application;

import com.boardjunit5practice.board.entity.Board;
import com.boardjunit5practice.board.form.BoardForm.*;
import com.boardjunit5practice.config.QuerydslConfigTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Import(QuerydslConfigTest.class)
public class BoardServiceTest {

    @Mock
    BoardService boardService;

    @Mock
    BoardRepository boardRepository;

    @DisplayName("등록")
    @Test
    void register(){
        //given
        final Board board = new Board("hi","hgstudy",0L);

        //when
        when(boardService.save(board)).thenReturn(board);
        final Board savedBoard = boardService.save(board);

        //then
        assertEquals(board.getTitle(), savedBoard.getTitle());
        verify(boardService).save(board);
    }

    @DisplayName("조회수 증가 확인")
    @Test
    void hit(){
        //given
        final Board board = new Board("hi","hgstudy",0L);
        boardRepository.save(board);
        given(boardService.incrementHit(board.getId())).willReturn(1L);

        //when
        Long incrementHit = boardService.incrementHit(board.getId());

        //then
        assertEquals(incrementHit,1L);
        verify(boardRepository).save(board);
        verify(boardService).incrementHit(board.getId());
    }

    @DisplayName("수정")
    @Test
    void modify(){
        //given
        final Board board = new Board("hi","hgstudy",0L);
        final Board modifyBoard = new Board("modifyTitle","modifyContent",1L);
        final Request.Modify modifyDto = new Request.Modify("modifyTitle","modifyContent",1L);

        //when
        when(boardService.update(1L,modifyBoard)).thenReturn(modifyBoard);
        final Board modifiedBoard = boardService.update(1L,modifyBoard);

        //then
        assertNotEquals(board.getTitle(), modifiedBoard.getTitle());
        assertEquals(board.getTitle(), "hi");
        assertEquals(modifiedBoard.getTitle(), "modifyTitle");
        verify(boardService).update(1L, modifyBoard);
    }
}
