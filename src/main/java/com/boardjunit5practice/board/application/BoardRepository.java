package com.boardjunit5practice.board.application;

import com.boardjunit5practice.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
