package com.example.codingbottleserver.application.service;

import com.example.codingbottleserver.api.dto.request.BoardReqDto;
import com.example.codingbottleserver.api.dto.response.BoardResDto;
import com.example.codingbottleserver.domain.entity.Board;
import com.example.codingbottleserver.domain.entity.Member;
import com.example.codingbottleserver.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberService memberService;

    @Transactional
    public BoardResDto create(BoardReqDto boardReqDto) {
        Member member = memberService.findById(boardReqDto.getMemberId());

        Board board = Board.builder()
                .title(boardReqDto.getTitle())
                .content(boardReqDto.getContent())
                .member(member)
                .build();

        Board saveBoard = boardRepository.save(board);

        return BoardResDto.of(saveBoard);
    }

    @Transactional(readOnly = true)
    public BoardResDto findById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        return BoardResDto.of(board);
    }

    @Transactional
    public BoardResDto updateById(Long id, BoardReqDto boardReqDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        board.update(boardReqDto.getTitle(), boardReqDto.getContent());

        return BoardResDto.of(board);
    }

    @Transactional
    public void deleteById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        boardRepository.delete(board);
    }
}
