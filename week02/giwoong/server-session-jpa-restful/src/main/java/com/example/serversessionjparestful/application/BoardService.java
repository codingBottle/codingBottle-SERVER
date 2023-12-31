package com.example.serversessionjparestful.application;

import com.example.serversessionjparestful.api.dto.request.BoardSaveReqDto;
import com.example.serversessionjparestful.api.dto.response.BoardResDto;
import com.example.serversessionjparestful.domain.Board;
import com.example.serversessionjparestful.domain.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    /**
     * 게시판 작성
     * 확인을 위해 저장하고 반환해주기
     * @return id, 제목, 내용, 작성날짜
     */
    @Transactional
    public BoardResDto boardSave(BoardSaveReqDto boardSaveReqDto) {
        Board board = Board.builder()
                .title(boardSaveReqDto.getTitle())
                .contents(boardSaveReqDto.getContents())
                .createDate(String.valueOf(LocalDate.now())).build();

        boardRepository.save(board);

        return new BoardResDto(board.getBoardId(),
                board.getTitle(),
                board.getContents(),
                board.getCreateDate());
    }

    /**
     * 게시판 리스트
     */
    public List<BoardResDto> boradList() {
        List<Board> boardList = boardRepository.findAll();

        List<BoardResDto> boardResDtoList = new ArrayList<>();
        for (Board board : boardList) {
            BoardResDto boardResDto = new BoardResDto(
                    board.getBoardId(),
                    board.getTitle(),
                    board.getContents(),
                    board.getCreateDate());

            boardResDtoList.add(boardResDto);
        }

        return boardResDtoList;
    }

    /**
     * 게시판 수정
     * 확인을 위해 수정하고 반환하기
     */
    @Transactional
    public BoardResDto boardUpdate(Long boardId, BoardSaveReqDto boardSaveReqDto) {
        Board board = boardRepository.findById(boardId).orElseThrow();

        board.update(boardSaveReqDto.getTitle(),
                boardSaveReqDto.getContents());

        return new BoardResDto(board.getBoardId(),
                board.getTitle(),
                board.getContents(),
                board.getCreateDate());
    }

    /**
     * 게시판 삭제
     */
    @Transactional
    public void boardDelete(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        boardRepository.delete(board);
    }
}

