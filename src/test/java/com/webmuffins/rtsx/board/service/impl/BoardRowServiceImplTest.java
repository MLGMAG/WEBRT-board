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
import org.mockito.junit.jupiter.MockitoExtension;

import com.webmuffins.rtsx.board.dto.boardrow.BoardRowRequestDto;
import com.webmuffins.rtsx.board.dto.boardrow.BoardRowResponseDto;
import com.webmuffins.rtsx.board.entity.BoardRow;
import com.webmuffins.rtsx.board.exception.NotFoundException;
import com.webmuffins.rtsx.board.mapper.Mapper;
import com.webmuffins.rtsx.board.repository.BoardRowRepository;

@ExtendWith(MockitoExtension.class)
class BoardRowServiceImplTest {

    private static final UUID DEFAULT_ID = UUID.randomUUID();
    private static final String DEFAULT_TITLE = "title";

    private BoardRow boardRow;
    private BoardRowRequestDto boardRowRequestDto;
    private BoardRowResponseDto boardRowResponseDto;
    private List<BoardRow> boardRowList;
    private List<BoardRowResponseDto> boardRowResponseDtoList;

    @Mock
    private BoardRowRepository boardRowRepository;

    @Mock
    private Mapper<BoardRow, BoardRowRequestDto, BoardRowResponseDto> boardRowMapper;

    @InjectMocks
    private BoardRowServiceImpl testInstance;

    @BeforeEach
    void setUp() {
        boardRow = new BoardRow();
        boardRowList = Collections.singletonList(boardRow);

        boardRowRequestDto = new BoardRowRequestDto();

        boardRowResponseDto = new BoardRowResponseDto();
        boardRowResponseDtoList = Collections.singletonList(boardRowResponseDto);

        boardRow.setTitle(DEFAULT_TITLE);
        boardRowResponseDto.setTitle(DEFAULT_TITLE);
        boardRowRequestDto.setTitle(DEFAULT_TITLE);
        boardRow.setId(DEFAULT_ID);
        boardRowResponseDto.setId(DEFAULT_ID);
    }

    @Test
    void shouldGetAllBoardRows() {
        when(boardRowRepository.findAll()).thenReturn(boardRowList);
        when(boardRowMapper.mapEntityListToDtoList(boardRowList)).thenReturn(boardRowResponseDtoList);

        List<BoardRowResponseDto> actual = testInstance.getAllBoardRows();

        assertThat(actual).isNotNull()
                .isEqualTo(boardRowResponseDtoList);
    }

    @Test
    void shouldGetBoardRowById() {
        when(boardRowRepository.findById(DEFAULT_ID)).thenReturn(Optional.of(boardRow));
        when(boardRowMapper.mapEntityToDto(boardRow)).thenReturn(boardRowResponseDto);

        BoardRowResponseDto actual = testInstance.getBoardRowById(DEFAULT_ID);

        assertThat(actual).isNotNull()
                .isEqualTo(boardRowResponseDto);
    }

    @Test
    void shouldThrowExceptionWhenDidNotGetBoardRowEntityById() {
        when(boardRowRepository.findById(DEFAULT_ID)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () ->  testInstance.getBoardRowById(DEFAULT_ID));
    }

    @Test
    void shouldCreateBoardRow() {
        when(boardRowMapper.mapDtoToEntity(boardRowRequestDto)).thenReturn(boardRow);
        when(boardRowRepository.save(boardRow)).thenReturn(boardRow);
        when(boardRowMapper.mapEntityToDto(boardRow)).thenReturn(boardRowResponseDto);

        BoardRowResponseDto actual = testInstance.createBoardRow(boardRowRequestDto);

        assertThat(actual).isNotNull()
                .isEqualTo(boardRowResponseDto);
    }

    @Test
    void shouldUpdateBoardRowById() {
        when(boardRowRepository.existsById(DEFAULT_ID)).thenReturn(true);
        when(boardRowMapper.mapDtoToEntity(boardRowRequestDto)).thenReturn(boardRow);
        when(boardRowRepository.save(boardRow)).thenReturn(boardRow);
        when(boardRowMapper.mapEntityToDto(boardRow)).thenReturn(boardRowResponseDto);

        BoardRowResponseDto actual = testInstance.updateBoardRowById(DEFAULT_ID, boardRowRequestDto);

        assertThat(actual).isNotNull()
                .isEqualTo(boardRowResponseDto);
    }

    @Test
    void shouldDeleteBoardRowById() {
        when(boardRowRepository.existsById(DEFAULT_ID)).thenReturn(true);

        testInstance.deleteBoardRowById(DEFAULT_ID);

        verify(boardRowRepository).deleteById(DEFAULT_ID);
    }
}
