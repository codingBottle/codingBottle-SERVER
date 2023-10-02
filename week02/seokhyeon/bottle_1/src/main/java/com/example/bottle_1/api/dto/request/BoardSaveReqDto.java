package com.example.bottle_1.api.dto.request;

import com.example.bottle_1.domain.Board;
import com.example.bottle_1.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardSaveReqDto {
    private String boardName;

    private String title;

    private String content;

    private Member member;

    public BoardSaveReqDto(String boardName, String title, String content, Member member) {
        this.boardName = boardName;
        this.title = title;
        this.content = content;
        this.member = member;

    }

    public Board toEntity() {
        return Board.builder()
                .member(member)
                .boardName(boardName)
                .title(title)
                .content(content)
                .build();
    }

}
