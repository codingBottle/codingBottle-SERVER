package com.week2.jpa.board.controller;

import com.week2.jpa.board.dto.BoardPatchDto;
import com.week2.jpa.board.dto.BoardPostDto;
import com.week2.jpa.board.dto.BoardResponseDto;
import com.week2.jpa.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<BoardResponseDto> postBoard(@RequestBody BoardPostDto boardPostDto) {
        BoardResponseDto boardResponseDto = boardService.createBoard(boardPostDto);

        return new ResponseEntity<>(boardResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<BoardResponseDto> getBoard(@RequestParam("boardId") Long boardId) {
        BoardResponseDto boardResponseDto = boardService.findBoard(boardId);

        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<BoardResponseDto> updateBoard(@RequestBody BoardPatchDto boardPatchDto) {
        BoardResponseDto boardResponseDto = boardService.updateBoard(boardPatchDto);

        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK) ;
    }

    @DeleteMapping
    public ResponseEntity deleteBoard(@RequestParam("boardId") Long boardId) {
        boardService.deleteBoard(boardId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
