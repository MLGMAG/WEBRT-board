package com.webmuffins.rtsx.board.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.webmuffins.rtsx.board.dto.board.BoardRequestDto;
import com.webmuffins.rtsx.board.dto.board.BoardResponseDto;
import com.webmuffins.rtsx.board.entity.Board;

class BoardMapperTest {

    private static final UUID DEFAULT_ID = UUID.randomUUID();
    private static final String DEFAULT_TITLE = "title";

    private Board board;
    private BoardResponseDto boardResponseDto;
    private BoardRequestDto boardRequestDto;

    private final BoardMapper testInstance = new BoardMapper();

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
    }

    @Test
    void shouldMapEntityToDto() {
        BoardResponseDto actual = testInstance.mapEntityToDto(board);

        assertThat(actual).isNotNull()
                .isEqualTo(boardResponseDto);
    }

    @Test
    void shouldMapDtoToEntity() {
        board.setId(null);
        Board actual = testInstance.mapDtoToEntity(boardRequestDto);

        assertThat(actual).isNotNull()
                .isEqualTo(board);
    }
}
