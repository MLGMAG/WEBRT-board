package com.webmuffins.rtsx.board.mapper;

import org.springframework.stereotype.Component;

import com.webmuffins.rtsx.board.dto.board.BoardRequestDto;
import com.webmuffins.rtsx.board.dto.board.BoardResponseDto;
import com.webmuffins.rtsx.board.entity.Board;

@Component("boardMapper")
public class BoardMapper implements Mapper<Board, BoardRequestDto, BoardResponseDto> {

    @Override
    public BoardResponseDto mapEntityToDto(Board board) {
        BoardResponseDto dto = new BoardResponseDto();
        dto.setTitle(board.getTitle());
        dto.setId(board.getId());
        return dto;
    }

    @Override
    public Board mapDtoToEntity(BoardRequestDto boardRequestDto) {
        Board entity = new Board();
        entity.setTitle(boardRequestDto.getTitle());
        return entity;
    }

}
