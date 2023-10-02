package com.week2.jpa.board.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardPatchDto {
    private Long boardId;

    private Long memberId;

    private String title;

    private String body;
}
