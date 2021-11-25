package com.webmuffins.rtsx.board.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.webmuffins.rtsx.board.dto.board.BoardRequestDto;
import com.webmuffins.rtsx.board.dto.board.BoardResponseDto;
import com.webmuffins.rtsx.board.dto.boardrow.BoardRowResponseDto;
import com.webmuffins.rtsx.board.entity.Board;
import com.webmuffins.rtsx.board.exception.NotFoundException;
import com.webmuffins.rtsx.board.mapper.BoardMapper;
import com.webmuffins.rtsx.board.repository.BoardRepository;
import com.webmuffins.rtsx.board.service.BoardRowService;

@ExtendWith(MockitoExtension.class)
class BoardServiceImplTest {

    private static final UUID DEFAULT_ID = UUID.randomUUID();
    private static final String DEFAULT_TITLE = "title";

    private Board board;
    private BoardResponseDto boardResponseDto;
    private BoardRowResponseDto boardRowResponseDto;
    private List<BoardRowResponseDto> boardRowResponseDtoList;
    private BoardRequestDto boardRequestDto;

    @Mock
    private BoardMapper boardMapper;

    @Mock
    private BoardRepository boardRepository;

    @Mock
    private BoardRowService boardRowService;

    @InjectMocks
    private BoardServiceImpl testInstance;

    @BeforeEach
    void setUp() {
        board = new Board();
        boardRequestDto = new BoardRequestDto();
        boardResponseDto = new BoardResponseDto();

        board.setId(DEFAULT_ID);
        board.setTitle(DEFAULT_TITLE);
        boardRequestDto.setTitle(DEFAULT_TITLE);
        boardResponseDto.setTitle(DEFAULT_TITLE);
        boardResponseDto.setId(DEFAULT_ID);
        boardRowResponseDto = new BoardRowResponseDto();
        boardRowResponseDto.setBoardId(DEFAULT_ID);
        boardRowResponseDtoList = Collections.singletonList(boardRowResponseDto);
        boardResponseDto.setRows(boardRowResponseDtoList);
    }

    @Test
    void shouldGetBoardResponseById() {
        when(boardRepository.findById(DEFAULT_ID)).thenReturn(Optional.of(board));
        when(boardMapper.mapEntityToDto(board)).thenReturn(boardResponseDto);

        BoardResponseDto actual = testInstance.getBoardById(DEFAULT_ID);

        assertThat(actual).isNotNull()
                .isEqualTo(boardResponseDto);
    }

    @Test
    void shouldThrowExceptionWhenDidNotFindBoard() {
        when(boardRepository.findById(DEFAULT_ID)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> testInstance.getBoardEntityById(DEFAULT_ID));
    }

    @Test
    void shouldCreateBoard() {
        when(boardMapper.mapDtoToEntity(boardRequestDto)).thenReturn(board);
        when(boardRepository.save(board)).thenReturn(board);
        when(boardMapper.mapEntityToDto(board)).thenReturn(boardResponseDto);

        BoardResponseDto actual = testInstance.createBoard(boardRequestDto);

        assertThat(actual).isNotNull()
                .isEqualTo(boardResponseDto);
    }

    @Test
    void shouldDeleteById() {
        when(boardRepository.existsById(DEFAULT_ID)).thenReturn(true);

        testInstance.deleteBoardById(DEFAULT_ID);

        verify(boardRepository).deleteById(DEFAULT_ID);
    }

    @Test
    void shouldUpdateById() {
        when(boardRepository.existsById(DEFAULT_ID)).thenReturn(true);
        when(boardMapper.mapDtoToEntity(boardRequestDto)).thenReturn(board);
        when(boardRepository.save(board)).thenReturn(board);
        board.setId(DEFAULT_ID);

        when(boardMapper.mapEntityToDto(board)).thenReturn(boardResponseDto);

        BoardResponseDto actual = testInstance.updateBoardById(DEFAULT_ID, boardRequestDto);

        assertThat(actual).isNotNull()
                .isEqualTo(boardResponseDto);
    }

}
