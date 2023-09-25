package com.example.codingbottleserver.api.controller;

import com.example.codingbottleserver.api.dto.request.BoardReqDto;
import com.example.codingbottleserver.api.dto.response.BoardResDto;
import com.example.codingbottleserver.application.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/boards")
    public ResponseEntity<BoardResDto> save(@RequestBody BoardReqDto boardReqDto) {
        BoardResDto board = boardService.create(boardReqDto);

        return ResponseEntity.ok(board);
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardResDto> findById(@PathVariable(value = "id") Long id) {
        BoardResDto board = boardService.findById(id);

        return ResponseEntity.ok(board);
    }
}
