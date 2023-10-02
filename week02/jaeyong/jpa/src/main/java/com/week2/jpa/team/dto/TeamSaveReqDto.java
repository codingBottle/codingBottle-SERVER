package com.week2.jpa.team.dto;


import com.week2.jpa.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamSaveReqDto {
    private Member.Part part;
    private String teamName;

    public TeamSaveReqDto(Member.Part part, String teamName) {
        this.part = part;
        this.teamName = teamName;
    }
}
