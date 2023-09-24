package com.example.serversessionjparestful.api.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResDto {
    private Long boardId;
    private String title;
    private String contents;
    private String createDate;

    public BoardResDto(Long boardId, String title, String contents, String createDate) {
        this.boardId = boardId;
        this.title = title;
        this.contents = contents;
        this.createDate = createDate;
    }
}
