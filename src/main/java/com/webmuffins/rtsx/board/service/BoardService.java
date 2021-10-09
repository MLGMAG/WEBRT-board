package com.webmuffins.rtsx.board.service;

import java.util.UUID;

import com.webmuffins.rtsx.board.dto.board.BoardRequestDto;
import com.webmuffins.rtsx.board.dto.board.BoardResponseDto;
import com.webmuffins.rtsx.board.entity.Board;

public interface BoardService {

    BoardResponseDto getBoardById(UUID id);

    BoardResponseDto createBoard(BoardRequestDto dto);

    BoardResponseDto updateBoardById(UUID id, BoardRequestDto dto);

    void deleteBoardById(UUID id);

    Board getBoardEntityById(UUID id);

}
