package com.example.serversessionjparestful.api.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResDto {
    private Long noticeId;
    private String title;
    private String contents;
    private String createDate;

    public BoardResDto(Long noticeId, String title, String contents, String createDate) {
        this.noticeId = noticeId;
        this.title = title;
        this.contents = contents;
        this.createDate = createDate;
    }
}
