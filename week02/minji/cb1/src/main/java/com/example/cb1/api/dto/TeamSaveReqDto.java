package com.example.cb1.api.dto;


import com.example.cb1.domain.Part;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamSaveReqDto {
    private Part part;
    private String teamName;

    public TeamSaveReqDto(Part part, String teamName) {
        this.part = part;
        this.teamName = teamName;
    }
}
