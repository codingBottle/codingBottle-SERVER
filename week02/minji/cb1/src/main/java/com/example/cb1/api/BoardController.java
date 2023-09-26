package com.example.cb1.api;

import com.example.cb1.application.BoardService;
import com.example.cb1.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    //등록
    @PostMapping
    public Board createBoard(@RequestBody Board board) {
        return boardService.createBoard(board);
    }

    // 특정 게시물 조회
    @GetMapping("/{boardId}")
    public Board getBoardById(@PathVariable Long boardId) {
        return boardService.getBoardById(boardId);
    }

    // 전체 게시물 목록 조회
    @GetMapping
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    //수정
    @PutMapping("/{boardId}")
    public Board updateBoard(@PathVariable Long boardId, @RequestBody Board updatedBoard) {
        return boardService.updateBoard(boardId, updatedBoard);
    }

    //삭제
    @DeleteMapping("/{boardId}")
    public void deleteBoard(@PathVariable Long postId) {

        boardService.deleteBoard(postId);
    }
}
