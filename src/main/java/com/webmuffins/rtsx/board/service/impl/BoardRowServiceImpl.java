package com.webmuffins.rtsx.board.service.impl;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.webmuffins.rtsx.board.dto.boardrow.BoardRowResponseDto;
import com.webmuffins.rtsx.board.dto.boardrow.BoardRowRequestDto;
import com.webmuffins.rtsx.board.entity.BoardRow;
import com.webmuffins.rtsx.board.exception.BadRequestException;
import com.webmuffins.rtsx.board.exception.NotFoundException;
import com.webmuffins.rtsx.board.mapper.Mapper;
import com.webmuffins.rtsx.board.repository.BoardRowRepository;
import com.webmuffins.rtsx.board.service.BoardRowService;
import com.webmuffins.rtsx.board.service.TicketService;

@Service
public class BoardRowServiceImpl implements BoardRowService {

    private static final Logger LOG = LoggerFactory.getLogger(BoardRowServiceImpl.class);

    private final BoardRowRepository boardRowRepository;
    private final TicketService ticketService;
    private final Mapper<BoardRow, BoardRowRequestDto, BoardRowResponseDto> boardRowMapper;

    public BoardRowServiceImpl(BoardRowRepository boardRowRepository, TicketService ticketService, Mapper<BoardRow, BoardRowRequestDto, BoardRowResponseDto> boardRowMapper) {
        this.boardRowRepository = boardRowRepository;
        this.ticketService = ticketService;
        this.boardRowMapper = boardRowMapper;
    }

    @Override
    public List<BoardRowResponseDto> getAllBoardRows() {
        List<BoardRow> rows = boardRowRepository.findAll();
        return boardRowMapper.mapEntityListToDtoList(rows);
    }

    @Override
    public BoardRowResponseDto getBoardRowById(UUID id) {
        BoardRow row = getBoardRowEntityById(id);
        return boardRowMapper.mapEntityToDto(row);
    }

    @Override
    public BoardRowResponseDto createBoardRow(BoardRowRequestDto dto) {
        BoardRow row = boardRowMapper.mapDtoToEntity(dto);
        BoardRow savedRow = boardRowRepository.save(row);
        return boardRowMapper.mapEntityToDto(savedRow);
    }

    @Override
    public BoardRowResponseDto updateBoardRowById(UUID id, BoardRowRequestDto dto) {
        checkIfRowExistsById(id);
        BoardRow rowToUpdate = boardRowMapper.mapDtoToEntity(dto);
        rowToUpdate.setId(id);
        BoardRow updatedRow = boardRowRepository.save(rowToUpdate);
        return boardRowMapper.mapEntityToDto(updatedRow);
    }

    @Override
    @Transactional
    public void deleteBoardRowById(UUID id) {
        checkIfRowExistsById(id);
        checkRowCanBeDeleted(id);
        boardRowRepository.deleteById(id);
        LOG.info("Delete row with id : {}", id);
    }

    private void checkRowCanBeDeleted(UUID id) {
        BoardRow boardRow = getBoardRowEntityById(id);
        if(!boardRow.getTickets().isEmpty()) {
            throw new BadRequestException("Can not delete row, that contains at least one ticket");
        }
    }

    @Override
    public BoardRow getBoardRowEntityById(UUID id) {
        return boardRowRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can not find row with such id"));
    }

    @Override
    public List<BoardRow> getBoardRowEntities() {
        return boardRowRepository.findAll();
    }

    @Override
    public List<BoardRowResponseDto> getBoardRowsByBoardId(UUID boardId) {
        List<BoardRow> boardRows = boardRowRepository.findBoardRowByBoard_Id(boardId);
        List<BoardRowResponseDto> boardRowResponseDtos = boardRowMapper.mapEntityListToDtoList(boardRows);
        boardRowResponseDtos
                .forEach(boardRowResponseDto -> boardRowResponseDto.setTickets(ticketService.getTicketsByRowId(boardRowResponseDto.getId())));
        return boardRowResponseDtos;
    }

    private void checkIfRowExistsById(UUID id) {
        if (!boardRowRepository.existsById(id)) {
            throw new NotFoundException("Can not find row with such id");
        }
    }
}
