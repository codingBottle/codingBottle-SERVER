package com.example.serversessionjparestful.api.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardSaveReqDto {
    private String title;
    private String contents;
    private String createDate;

    public BoardSaveReqDto(String title, String contents, String createDate) {
        this.title = title;
        this.contents = contents;
        this.createDate = createDate;
    }
}
