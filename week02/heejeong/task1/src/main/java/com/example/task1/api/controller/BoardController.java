package com.example.task1.api.controller;

import com.example.task1.api.common.RspTemple;
import com.example.task1.api.dto.request.BoardSaveReq;
import com.example.task1.api.dto.request.BoardUpdateReq;
import com.example.task1.api.dto.response.BoardList;
import com.example.task1.application.BoardService;
import com.example.task1.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    컨트롤러 설정
 */
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /*
        게시판 글 등록
     */
    @PostMapping("/boards")
    public ResponseEntity<RspTemple<Void>> create(@RequestBody BoardSaveReq boardSaveReq) {

        long boardId = boardService.create(boardSaveReq);

        RspTemple<Void> rspTemple = new RspTemple<>(HttpStatus.CREATED, boardId + "번 글 등록 성공");

        return ResponseEntity.status(HttpStatus.CREATED).body(rspTemple);
    }

    /*
        게시판 글 조회
     */
    @GetMapping("/boards")
    public ResponseEntity<BoardList> getBoards() {
//      Board Entity 객체
        List<Board> boards = boardService.findBoards();

//      Entity 객체 DTO로 변환
        BoardList boardList = new BoardList(boards);

        return ResponseEntity.ok(boardList);
    }

    /*
        게시판 글 수정
     */
    @PutMapping("/boards/{boardId}")
    public ResponseEntity<Void> update(@PathVariable long boardId, @RequestBody BoardUpdateReq updateReq) {

        boardService.update(boardId, updateReq);

        return ResponseEntity.noContent().build();
    }

    /*
        게시판 글 삭제
     */
    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity<Void> delete(@PathVariable long boardId) {
        boardService.delete(boardId);

        return ResponseEntity.noContent().build();
    }
}
