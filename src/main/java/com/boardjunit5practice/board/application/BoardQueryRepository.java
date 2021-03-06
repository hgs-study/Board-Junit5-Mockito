package com.boardjunit5practice.board.application;

import com.boardjunit5practice.board.form.BoardForm.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.boardjunit5practice.board.entity.QBoard.board;

@Repository
@RequiredArgsConstructor
public class BoardQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Long incrementHit(Long id){
        jpaQueryFactory
                .update(board)
                .where(board.id.eq(id))
                .set(board.hit, board.hit.add(1L))
                .execute();

        return id;
    }

}
