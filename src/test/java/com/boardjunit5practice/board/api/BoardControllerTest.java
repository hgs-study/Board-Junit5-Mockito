package com.boardjunit5practice.board.api;

import com.boardjunit5practice.board.application.BoardService;
import com.boardjunit5practice.board.entity.Board;
import com.boardjunit5practice.board.form.BoardForm;
import com.boardjunit5practice.board.form.BoardForm.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class BoardControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    BoardService boardService;

    @DisplayName("글쓰기")
    @Test
    void register() throws Exception {
        final Board board = new Board("testTitle","testContent");

        when(boardService.save(any(Board.class))).thenReturn(board);

        mockMvc.perform(post("/boards")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"title\":\"testTitle\", \"content\" : \"testContent\" }"))
               .andExpect(status().isOk())
               .andExpect(content().string(containsString(board.getContent())))
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andDo(print());

        verify(boardService).save(any(Board.class));
    }

    @DisplayName("글 조회")
    @Test
    void getBoard() throws Exception {
        final Long id = 1L;
        final String title = "testTitle";
        Board board = new Board(title,"testContent");

        when(boardService.findById(id)).thenReturn(board);

        mockMvc.perform(get("/boards/1"))
               .andExpect(status().isOk())
               .andExpect(content().string(containsString(title)))
               .andDo(print());

        verify(boardService).findById(id);
    }

    @DisplayName("글 수정")
    @Test
    void modifyBoard() throws Exception {
        final Long id = 1L;
        final Board modifyBoard = new Board("modifyTitle","modifyContent");

        when(boardService.update(id,modifyBoard)).thenReturn(modifyBoard);

        mockMvc.perform(patch("/boards/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"modifyTitle\", \"content\" : \"modifyContent\" }"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("글 삭제")
    @Test
    void deleteBoard() throws Exception {
        final Long id = 1L;
        final Board board = new Board("deleteBoard","content");

        when(boardService.delete(id)).thenReturn(board);

        mockMvc.perform(delete("/boards/{id}",id))
               .andExpect(status().isOk())
               .andExpect(content().string(containsString(board.getTitle())))
               .andDo(print());

        verify(boardService).delete(id);
    }
}
