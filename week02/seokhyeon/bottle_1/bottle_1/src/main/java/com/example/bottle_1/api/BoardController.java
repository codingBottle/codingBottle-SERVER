package com.example.bottle_1.api;

import com.example.bottle_1.api.dto.request.BoardSaveReqDto;
import com.example.bottle_1.api.dto.request.BoardUpdateReqDto;
import com.example.bottle_1.api.dto.response.MemberResDto;
import com.example.bottle_1.application.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board/list") //멤버의 게시판 리스트
    public ResponseEntity<List<MemberResDto>> boardList(@RequestParam("memberId")Long Id){
        return new ResponseEntity<>(boardService.MemberBoardList(Id), HttpStatus.OK);
    }

    @PostMapping("/board") //게시판 생성
    public Long create(@RequestBody BoardSaveReqDto boardSaveReqDto){
        return boardService.create(boardSaveReqDto);
    }

    @PutMapping("/board/{id}") //게시판 수정
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody BoardUpdateReqDto boardUpdateReqDto){
        boardService.update(id, boardUpdateReqDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/board/{id}") //게시판 삭제
    public ResponseEntity<Void> delete(@PathVariable Long id){
        boardService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
