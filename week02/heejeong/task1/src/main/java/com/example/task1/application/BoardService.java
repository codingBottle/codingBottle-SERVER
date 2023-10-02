package com.example.task1.application;


import com.example.task1.api.dto.request.BoardSaveReq;
import com.example.task1.api.dto.request.BoardUpdateReq;
import com.example.task1.domain.Board;
import com.example.task1.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    /*
     * 공지사항 등록
     * 글 등록 후 저장된 컬럼의 pk 반환
     */
    @Transactional
    public long create(BoardSaveReq boardSaveReq) {

        Board board = new Board(boardSaveReq.getTitle(), boardSaveReq.getContent());
        
//      저장 후 글 번호로 리턴
        return boardRepository.save(board).getBoardId();
    }

    /*
     * 공지사항 조회
     * 최신 순으로 정렬하여 조회함
     */
    public List<Board> findBoards() {
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "boardId"));
    }

    /*
     * 공지사항 수정
     * 해당 글 번호가 없으면 예외 처리
     */
    @Transactional
    public void update(long id, BoardUpdateReq boardUpdateReq) {

        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("번호에 해당하는 글이 없습니다."));

//      글 번호가 있는 경우 수정
        board.update(boardUpdateReq.getTitle(), boardUpdateReq.getContent());
    }

    /*
        공지사항 삭제
        글 번호로 존재하는 글을 찾고, 해당 글 번호가 없으면 예외 처리
     */
    @Transactional
    public void delete(long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("번호에 해당하는 글이 없습니다."));

//      글 번호가 있는 경우 삭제
        boardRepository.deleteById(board.getBoardId());
    }
}
