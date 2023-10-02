package com.example.task1.api.dto.response;

import com.example.task1.domain.Part;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TeamRes {

    private long teamId;
    private String teamName;
    private Part part;

    @Builder
    public TeamRes(long teamId, String teamName, Part part) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.part = part;
    }

}
