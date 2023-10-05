package com.week2.jpa.board.mapper;

import com.week2.jpa.board.dto.BoardPatchDto;
import com.week2.jpa.board.dto.BoardPostDto;
import com.week2.jpa.board.dto.BoardResponseDto;
import com.week2.jpa.board.entity.Board;
import com.week2.jpa.board.repository.BoardRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    Board boardPostDtoToBoard(BoardPostDto boardPostDto);
    @Mapping(source = "member.memberId", target = "memberId")
    BoardResponseDto boardToBoardResponseDto(Board board);

    Board boardPatchDtoToBoard(BoardPatchDto boardPatchDto);
}
