package com.webmuffins.rtsx.board.service.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.webmuffins.rtsx.board.dto.boardrow.BoardRowResponseDto;
import com.webmuffins.rtsx.board.dto.boardrow.BoardRowRequestDto;
import com.webmuffins.rtsx.board.entity.BoardRow;
import com.webmuffins.rtsx.board.exception.NotFoundException;
import com.webmuffins.rtsx.board.mapper.Mapper;
import com.webmuffins.rtsx.board.repository.BoardRowRepository;
import com.webmuffins.rtsx.board.service.BoardRowService;

@Service
public class BoardRowServiceImpl implements BoardRowService {

    private static final Logger LOG = LoggerFactory.getLogger(BoardRowServiceImpl.class);

    private final BoardRowRepository boardRowRepository;
    private final Mapper<BoardRow, BoardRowRequestDto, BoardRowResponseDto> boardRowMapper;

    public BoardRowServiceImpl(BoardRowRepository boardRowRepository, Mapper<BoardRow, BoardRowRequestDto, BoardRowResponseDto> boardRowMapper) {
        this.boardRowRepository = boardRowRepository;
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
    public void deleteBoardRowById(UUID id) {
        checkIfRowExistsById(id);
        boardRowRepository.deleteById(id);
        LOG.info("Delete row with id : {}", id);
    }

    @Override
    public BoardRow getBoardRowEntityById(UUID id) {
        return boardRowRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can not find row with such id"));
    }

    private void checkIfRowExistsById(UUID id) {
        if (!boardRowRepository.existsById(id)) {
            throw new NotFoundException("Can not find row with such id");
        }
    }
}
