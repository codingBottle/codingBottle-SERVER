package com.example.serversessionjparestful.api.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardSaveReqDto {
    private String title;
    private String contents;

    public BoardSaveReqDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
