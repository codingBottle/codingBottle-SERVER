package com.example.bottle_1.application;

import com.example.bottle_1.api.dto.request.BoardSaveReqDto;
import com.example.bottle_1.api.dto.request.BoardUpdateReqDto;
import com.example.bottle_1.api.dto.response.MemberResDto;
import com.example.bottle_1.domain.Board;
import com.example.bottle_1.domain.Member;
import com.example.bottle_1.domain.repository.BoardRepository;
import com.example.bottle_1.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    public BoardService(BoardRepository boardRepository, MemberRepository memberRepository) {
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long create(BoardSaveReqDto boardSaveReqDto) {
       return boardRepository.save(boardSaveReqDto.toEntity()).getBoardId();
    }

    @Transactional
    public void update(Long id, BoardUpdateReqDto boardUpdateReqDto){
        Optional<Board> optionalBoard = boardRepository.findById(id);

        Board board = optionalBoard.orElseThrow(() -> new IllegalArgumentException("해당 id의 todo가 없습니다."));
        // todo는 Null이 아니다.

        board.update(boardUpdateReqDto.getBoardName(),boardUpdateReqDto.getContent(), boardUpdateReqDto.getTitle());

    }

    public List<MemberResDto> MemberBoardList(Long memberId) {
//        Board board = boardRepository.findById(memberId).orElseThrow();
//        Member member = board.getMember();
//        List<Board> boardList = member.getBoardList();
        //파라미터로 들어온 멤버 Id로 찾고 리스트로 보드를 찾아서 멤버 아이디로 분류
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 member의 board가 없습니다."));
        List<Board> boardList = boardRepository.findByMember(member);

        List<MemberResDto> memberResDtoList = new ArrayList<>();
        for (Board boardItem : boardList) {
            MemberResDto memberResDto = new MemberResDto(
                    boardItem.getBoardId(),
                    boardItem.getBoardName(),
                    boardItem.getContent(),
                    boardItem.getTitle(),
                    member.getMemberId()
            );
            memberResDtoList.add(memberResDto);
        }

        return memberResDtoList;
    }

    @Transactional
    public void delete(Long id){
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 board가 없습니다."));

        boardRepository.delete(board);
    }


}
