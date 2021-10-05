package com.webmuffins.rtsx.board.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.webmuffins.rtsx.board.dto.board.BoardRequestDto;
import com.webmuffins.rtsx.board.dto.board.BoardResponseDto;
import com.webmuffins.rtsx.board.service.BoardService;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/{id}")
    public BoardResponseDto getBoard(@PathVariable UUID id) {
        return boardService.getBoardById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto dto) {
        return boardService.createBoard(dto);
    }

    @PutMapping("/{id}")
    public BoardResponseDto updateBoard(@PathVariable UUID id, @RequestBody BoardRequestDto dto) {
        return boardService.updateBoardById(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBoard(@PathVariable UUID id) {
        boardService.deleteBoardById(id);
    }
}
