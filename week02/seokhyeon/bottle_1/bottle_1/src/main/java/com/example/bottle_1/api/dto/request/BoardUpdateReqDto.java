package com.example.bottle_1.api.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardUpdateReqDto {

    private String boardName;

    private String title;

    private String content;

    @Builder
    public BoardUpdateReqDto(String title, String content, String boardName) {
        this.boardName = boardName;
        this.title = title;
        this.content = content;
    }



}
