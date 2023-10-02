package com.example.codingbottleserver.api.dto.request;

import com.example.codingbottleserver.domain.entity.Part;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamReqDto {
    private String teamName;
    private Part part;

    public TeamReqDto(String teamName, Part part) {
        this.teamName = teamName;
        this.part = part;
    }
}
