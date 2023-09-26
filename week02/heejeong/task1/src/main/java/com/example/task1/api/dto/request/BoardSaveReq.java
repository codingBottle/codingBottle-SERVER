package com.example.task1.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/*
    글 작성 시 사용할 DTO
 */
@Getter
@NoArgsConstructor
public class BoardSaveReq {

    private String title;
    private String content;

    public BoardSaveReq(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
