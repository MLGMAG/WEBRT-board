package com.webmuffins.rtsx.board.service.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.webmuffins.rtsx.board.dto.board.BoardRequestDto;
import com.webmuffins.rtsx.board.dto.board.BoardResponseDto;
import com.webmuffins.rtsx.board.entity.Board;
import com.webmuffins.rtsx.board.exception.NotFoundException;
import com.webmuffins.rtsx.board.mapper.Mapper;
import com.webmuffins.rtsx.board.repository.BoardRepository;
import com.webmuffins.rtsx.board.service.BoardRowService;
import com.webmuffins.rtsx.board.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

    private static final Logger LOG = LoggerFactory.getLogger(BoardServiceImpl.class);

    private final BoardRepository boardRepository;
    private final BoardRowService boardRowService;
    private final Mapper<Board, BoardRequestDto, BoardResponseDto> boardMapper;

    public BoardServiceImpl(BoardRepository boardRepository, BoardRowService boardRowService, Mapper<Board, BoardRequestDto, BoardResponseDto> boardMapper) {
        this.boardRepository = boardRepository;
        this.boardRowService = boardRowService;
        this.boardMapper = boardMapper;
    }

    @Override
    @Transactional
    public BoardResponseDto getBoardById(UUID id) {
        Board board = getBoardEntityById(id);
        BoardResponseDto boardResponseDto = boardMapper.mapEntityToDto(board);
        boardResponseDto.setRows(boardRowService.getBoardRowsByBoardId(board.getId()));
        return boardResponseDto;
    }

    @Override
    public BoardResponseDto createBoard(BoardRequestDto dto) {
        Board board = boardMapper.mapDtoToEntity(dto);
        Board savedBoard = boardRepository.save(board);
        return boardMapper.mapEntityToDto(savedBoard);
    }

    @Override
    public BoardResponseDto updateBoardById(UUID id, BoardRequestDto dto) {
        checkIfExists(id);
        Board updatedBoard = boardMapper.mapDtoToEntity(dto);
        updatedBoard.setId(id);
        Board savedBoard = boardRepository.save(updatedBoard);
        return boardMapper.mapEntityToDto(savedBoard);
    }

    @Override
    public void deleteBoardById(UUID id) {
        checkIfExists(id);
        boardRepository.deleteById(id);
        LOG.info("Delete board with id : {}", id);
    }

    private void checkIfExists(UUID id) {
        if(!boardRepository.existsById(id)) {
            throw new NotFoundException("Can not find board with such id");
        }
    }

    @Override
    public Board getBoardEntityById(UUID id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can not find board with such id"));
    }
}
