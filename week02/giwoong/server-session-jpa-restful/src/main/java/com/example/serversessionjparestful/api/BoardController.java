package com.example.serversessionjparestful.api;

import com.example.serversessionjparestful.api.dto.request.BoardSaveReqDto;
import com.example.serversessionjparestful.api.dto.response.BoardResDto;
import com.example.serversessionjparestful.application.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    /**
     * 게시판 작성
     * 확인을 위해 저장하고 반환해주기
     *
     * @return id, 제목, 내용, 작성날짜
     */
    @PostMapping("/save")
    public ResponseEntity<BoardResDto> save(@RequestBody BoardSaveReqDto boardSaveReqDto) {
        BoardResDto boardResDto = boardService.boardSave(boardSaveReqDto);
        return new ResponseEntity<>(boardResDto, HttpStatus.OK);
    }

    /**
     * 게시판 리스트
     */
    @GetMapping("/list")
    public ResponseEntity<List<BoardResDto>> list() {
        return new ResponseEntity<>(boardService.boradList(), HttpStatus.OK);
    }

    /**
     * 게시판 수정
     * 확인을 위해 수정하고 반환하기
     */
    @PutMapping("/update")
    public ResponseEntity<BoardResDto> update(@RequestParam("boardId") Long boardId, @RequestBody BoardSaveReqDto boardSaveReqDto) {
        BoardResDto boardResDto = boardService.boardUpdate(boardId, boardSaveReqDto);
        return new ResponseEntity<>(boardResDto, HttpStatus.OK);
    }

    /**
     * 게시판 삭제
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("boardId") Long boardId) {
        boardService.boardDelete(boardId);
        return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
    }

}
