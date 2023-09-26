package com.example.task1.api.dto.request;

import com.example.task1.domain.Part;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
    팀 저장 시 사용할 DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamSaveReq {

    private Part part;

    private String teamName;

    public TeamSaveReq(Part part, String teamName) {
        this.part = part;
        this.teamName = teamName;
    }
}
