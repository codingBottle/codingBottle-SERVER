package com.example.bottle_1.api.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResDto {
    private Long id;

    private String boardName;

    private String title;

    private String content;

    private Long memberId;

    public MemberResDto(Long id, String boardName, String title, String content, Long memberId) {
        this.id = id;
        this.boardName = boardName;
        this.title = title;
        this.content = content;
        this.memberId = memberId;
    }
}
