package com.week2.jpa.board.service;

import com.week2.jpa.board.dto.BoardPatchDto;
import com.week2.jpa.board.dto.BoardPostDto;
import com.week2.jpa.board.dto.BoardResponseDto;
import com.week2.jpa.board.entity.Board;
import com.week2.jpa.board.mapper.BoardMapper;
import com.week2.jpa.board.repository.BoardRepository;
import com.week2.jpa.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BoardService {
    private final BoardMapper boardMapper;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public BoardService(BoardMapper boardMapper, BoardRepository boardRepository, MemberRepository memberRepository) {
        this.boardMapper = boardMapper;
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public BoardResponseDto createBoard(BoardPostDto boardPostDto) {
        Board board = boardMapper.boardPostDtoToBoard(boardPostDto);
        board.setMember(memberRepository.findById(boardPostDto.getMemberId()).orElseThrow());
        board = boardRepository.save(board);

        return boardMapper.boardToBoardResponseDto(board);
    }

    public BoardResponseDto findBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("존재하지 않는 게시판입니다."));

        return boardMapper.boardToBoardResponseDto(board);
    }

    @Transactional
    public BoardResponseDto updateBoard(BoardPatchDto boardPatchDto) {
        Board board = boardRepository.findById(boardPatchDto.getBoardId()).orElseThrow();

        board = board.toBuilder()
                .title(boardPatchDto.getTitle())
                .body(boardPatchDto.getBody())
                .build();

        boardRepository.save(board);

        return boardMapper.boardToBoardResponseDto(board);
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }
}
