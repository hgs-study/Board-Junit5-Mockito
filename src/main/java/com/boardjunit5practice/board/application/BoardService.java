package com.boardjunit5practice.board.application;

import com.boardjunit5practice.board.entity.Board;
import com.boardjunit5practice.board.entity.QBoard;
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
    public void save(Board board){
        boardRepository.save(board);
    }

    public Board findById(Long id){
        final Board findBoard = boardRepository.findById(id)
                                                  .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
        incrementHit(findBoard.getId());
        return findBoard;
    }

    private void incrementHit(Long id){
        boardQueryRepository.incrementHit(id);
    }
}
