package com.example.codingbottleserver.api.dto.response;

import com.example.codingbottleserver.domain.entity.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResDto {
    private Long boardId;
    private String title;
    private String content;
    private MemberResDto member;

    @Builder
    public BoardResDto(Long boardId, String title, String content, MemberResDto member) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public static BoardResDto of(Board board) {
        return BoardResDto.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .content(board.getContent())
                .member(MemberResDto.of(board.getMember()))
                .build();
    }
}
