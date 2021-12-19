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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.webmuffins.rtsx.board.client.SecurityServiceProxyClient;
import com.webmuffins.rtsx.board.dto.board.BoardRequestDto;
import com.webmuffins.rtsx.board.dto.board.BoardResponseDto;
import com.webmuffins.rtsx.board.dto.user.UserDto;
import com.webmuffins.rtsx.board.service.BoardService;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;
    private final SecurityServiceProxyClient serviceProxyClient;

    public BoardController(BoardService boardService, SecurityServiceProxyClient serviceProxyClient) {
        this.boardService = boardService;
        this.serviceProxyClient = serviceProxyClient;
    }

    @GetMapping("/{id}")
    public BoardResponseDto getBoard(@PathVariable UUID id, @RequestHeader("Authorization") String token) {
        BoardResponseDto board = boardService.getBoardById(id);
        List<UserDto> allUsers = serviceProxyClient.getAllUsers(token);
        board.setAllUsers(allUsers);
        return board;
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
