package com.webmuffins.rtsx.board.service;

import java.util.List;
import java.util.UUID;

import com.webmuffins.rtsx.board.dto.boardrow.BoardRowResponseDto;
import com.webmuffins.rtsx.board.dto.boardrow.BoardRowRequestDto;
import com.webmuffins.rtsx.board.entity.BoardRow;

public interface BoardRowService {

    List<BoardRowResponseDto> getAllBoardRows();

    BoardRowResponseDto getBoardRowById(UUID id);

    BoardRowResponseDto createBoardRow(BoardRowRequestDto dto);

    BoardRowResponseDto updateBoardRowById(UUID id, BoardRowRequestDto dto);

    void deleteBoardRowById(UUID id);

    BoardRow getBoardRowEntityById(UUID id);

    List<BoardRow> getBoardRowEntities();

    List<BoardRowResponseDto> getBoardRowsByBoardId(UUID boardId);

}
