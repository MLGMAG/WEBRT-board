package com.webmuffins.rtsx.board.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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
import com.webmuffins.rtsx.board.entity.Board;
import com.webmuffins.rtsx.board.entity.BoardRow;
import com.webmuffins.rtsx.board.repository.BoardRepository;

@ExtendWith(MockitoExtension.class)
class BoardRowMapperTest {

    private static final UUID DEFAULT_ID = UUID.randomUUID();
    private static final String DEFAULT_TITLE = "title";

    private static final UUID DEFAULT_BOARD_ID = UUID.randomUUID();

    private BoardRow boardRow;
    private BoardRowRequestDto boardRowRequestDto;
    private BoardRowResponseDto boardRowResponseDto;

    private Board board;

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardRowMapper testInstance;

    @BeforeEach
    void setUp() {
        boardRow = new BoardRow();

        boardRowRequestDto = new BoardRowRequestDto();

        boardRowResponseDto = new BoardRowResponseDto();

        boardRow.setTitle(DEFAULT_TITLE);
        boardRowResponseDto.setTitle(DEFAULT_TITLE);
        boardRowRequestDto.setTitle(DEFAULT_TITLE);
        boardRow.setId(DEFAULT_ID);
        boardRowResponseDto.setId(DEFAULT_ID);

        board = new Board();
        board.setId(DEFAULT_BOARD_ID);
        boardRow.setBoard(board);
        boardRowResponseDto.setBoardId(DEFAULT_BOARD_ID);
        boardRowRequestDto.setBoardId(DEFAULT_BOARD_ID);
    }

    @Test
    void shouldMapEntityToDto() {
        BoardRowResponseDto actual = testInstance.mapEntityToDto(boardRow);

        assertThat(actual).isNotNull()
                .isEqualTo(boardRowResponseDto);

    }

    @Test
    void shouldMapDtoToEntity() {
        boardRow.setId(null);

        when(boardRepository.findById(DEFAULT_BOARD_ID)).thenReturn(Optional.of(board));

        BoardRow actual = testInstance.mapDtoToEntity(boardRowRequestDto);

        assertThat(actual).isNotNull()
                .isEqualTo(boardRow);
    }

}
