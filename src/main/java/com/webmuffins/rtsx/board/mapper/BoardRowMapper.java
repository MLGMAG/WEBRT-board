package com.webmuffins.rtsx.board.mapper;

import org.springframework.stereotype.Component;

import com.webmuffins.rtsx.board.dto.boardrow.BoardRowResponseDto;
import com.webmuffins.rtsx.board.dto.boardrow.BoardRowRequestDto;
import com.webmuffins.rtsx.board.entity.BoardRow;
import com.webmuffins.rtsx.board.exception.NotFoundException;
import com.webmuffins.rtsx.board.repository.BoardRepository;

@Component("boardRowMapper")
public class BoardRowMapper implements Mapper<BoardRow, BoardRowRequestDto, BoardRowResponseDto> {

    private final BoardRepository boardRepository;
    private final TicketMapper ticketMapper;

    public BoardRowMapper(BoardRepository boardRepository, TicketMapper ticketMapper) {
        this.boardRepository = boardRepository;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public BoardRowResponseDto mapEntityToDto(BoardRow entity) {
        BoardRowResponseDto dto = new BoardRowResponseDto();
        dto.setId(entity.getId());
        dto.setBoardId(entity.getBoard().getId());
        dto.setColor(entity.getColor());
        dto.setTitle(entity.getTitle());
        dto.setPositon(entity.getPosition());
        dto.setTickets(ticketMapper.mapEntityListToDtoList(entity.getTickets()));
        return dto;
    }

    @Override
    public BoardRow mapDtoToEntity(BoardRowRequestDto dto) {
        BoardRow entity = new BoardRow();
        entity.setBoard(boardRepository.findById(dto.getBoardId())
                .orElseThrow(() -> new NotFoundException("Can not create row on non existed board")));
        entity.setColor(dto.getColor());
        entity.setPosition(dto.getPosition());
        entity.setTitle(dto.getTitle());
        return entity;
    }
}
