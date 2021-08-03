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
    public Response.Find register(@RequestBody Request.Register register){
        final Board board = BoardMapper.INSTANCE.toEntity(register);
        boardService.save(board);

        return BoardMapper.INSTANCE.toDto(board);
    }

    @GetMapping("/boards/{id}")
    public Response.Find get(@PathVariable Long id){
        final Board board = boardService.findById(id);
        boardService.incrementHit(id); //조회수 증가

        return BoardMapper.INSTANCE.toDto(board);
    }

    @PatchMapping("/boards/{id}")
    public Response.Find modify(@PathVariable Long id, @RequestBody Request.Modify modify){
        final Board board = boardService.update(id,BoardMapper.INSTANCE.toEntity(modify));
        return BoardMapper.INSTANCE.toDto(board);
    }

    @DeleteMapping("/boards/{id}")
    public Response.Find delete(@PathVariable Long id){
        final Board board =boardService.delete(id);
        return BoardMapper.INSTANCE.toDto(board);
    }

}
