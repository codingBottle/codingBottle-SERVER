package com.example.cb1.application;

import com.example.cb1.domain.Board;
import com.example.cb1.domain.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //등록
    @Transactional
    public Board createBoard(Board board) {
        // 게시물을 저장하고 반환
        return boardRepository.save(board);
    }

    // 특정 게시물 조회
    @Transactional
    public Board getBoardById(Long boardId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        return optionalBoard.orElse(null);
    }

    // 전체 게시물 목록 조회
    @Transactional
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    //수정
    @Transactional
    public Board updateBoard(Long boardId, Board updatedBoard) {
        // postId를 사용하여 원본 게시물을 찾음
        Board existingBoard = boardRepository.findById(boardId).orElse(null);

        if (existingBoard != null) {
            // 업데이트된 내용으로 게시물 업데이트
            existingBoard.setTitle(updatedBoard.getTitle());
            existingBoard.setContent(updatedBoard.getContent());
            // 다른 필드 업데이트도 수행 가능

            // 업데이트된 게시물 저장 및 반환
            return boardRepository.save(existingBoard);
        } else {
            return null;
        }
    }

    //삭제
    @Transactional
    public void deleteBoard(Long boardId) {
        // postId를 사용하여 게시물 삭제
        boardRepository.deleteById(boardId);
    }
}
