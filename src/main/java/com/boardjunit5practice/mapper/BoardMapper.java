package com.boardjunit5practice.mapper;


import com.boardjunit5practice.board.entity.Board;
import com.boardjunit5practice.board.form.BoardForm.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BoardMapper {

    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    @Mappings({
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "content", source = "content"),
            @Mapping(target = "hit", defaultValue = "0L")
    })
    Board toEntity(Request.Register register);
    Board toEntity(Request.Modify modify);
    Response.Find toDto(Board board);
}
