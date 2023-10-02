package com.week2.jpa.board.dto;

import com.week2.jpa.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@AllArgsConstructor
public class BoardResponseDto {
    private Long boardId;

    private Long memberId;

    private String title;

    private String body;
}
