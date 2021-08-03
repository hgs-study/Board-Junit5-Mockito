package com.boardjunit5practice.board.api;

import com.boardjunit5practice.board.application.BoardService;
import com.boardjunit5practice.board.entity.Board;
import com.boardjunit5practice.board.form.BoardForm.*;
import com.boardjunit5practice.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/boards")
    public void register(@RequestBody Request.register register){
        final Board board = BoardMapper.INSTANCE.toEntity(register);
        boardService.save(board);
    }

    @GetMapping("/boards/{id}")
    public Response.Find get(@PathVariable Long id){
        final Board board = boardService.findById(id);

        return BoardMapper.INSTANCE.toDto(board);
    }


}
