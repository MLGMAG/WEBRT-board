package com.webmuffins.rtsx.board.controller;

import java.util.List;
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

import com.webmuffins.rtsx.board.dto.boardrow.BoardRowResponseDto;
import com.webmuffins.rtsx.board.dto.boardrow.BoardRowRequestDto;
import com.webmuffins.rtsx.board.service.BoardRowService;

@RestController
@RequestMapping("/rows")
public class BoardRowController {

    private final BoardRowService boardRowService;

    public BoardRowController(BoardRowService boardRowService) {
        this.boardRowService = boardRowService;
    }

    @GetMapping
    public List<BoardRowResponseDto> getAllBoardRows() {
        return boardRowService.getAllBoardRows();
    }

    @GetMapping("/{id}")
    public BoardRowResponseDto getTagById(@PathVariable UUID id) {
        return boardRowService.getBoardRowById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BoardRowResponseDto createTag(@RequestBody BoardRowRequestDto dto) {
        return boardRowService.createBoardRow(dto);
    }

    @PutMapping("/{id}")
    public BoardRowResponseDto updateTag(@PathVariable UUID id, @RequestBody BoardRowRequestDto dto) {
        return boardRowService.updateBoardRowById(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable UUID id) {
        boardRowService.deleteBoardRowById(id);
    }

}
