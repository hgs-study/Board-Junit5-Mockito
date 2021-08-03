package com.boardjunit5practice.board.application;

import com.boardjunit5practice.board.entity.Board;
import com.boardjunit5practice.board.entity.QBoard;
import com.boardjunit5practice.board.form.BoardForm.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardQueryRepository boardQueryRepository;

    @Transactional
    public Board save(Board board){
        return boardRepository.save(board);
    }

    public Board findById(Long id){
        return boardRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
    }

    public Board findByTitle(String title){
        return boardRepository.findByTitle(title);
    }

    public Long incrementHit(Long id){
        boardQueryRepository.incrementHit(id);

        return findById(id).getHit();
    }


    @Transactional
    public Board update(Long id, Board modifyBoard) {
        final Board board = findById(id);
       board.update(modifyBoard);

       return board;
    }

    @Transactional
    public Board delete(Long id) {
        final Board board = findById(id);
        boardRepository.delete(board);

        return board;
    }
}
