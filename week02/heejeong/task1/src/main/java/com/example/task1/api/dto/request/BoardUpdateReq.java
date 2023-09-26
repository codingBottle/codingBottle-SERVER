package com.example.task1.api.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
    글 수정 시 사용할 DTO
 */
@Getter
@Builder
@NoArgsConstructor
public class BoardUpdateReq {

    private String title;
    private String content;

    public BoardUpdateReq(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
