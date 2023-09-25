package com.example.codingbottleserver.api.dto.response;

import com.example.codingbottleserver.domain.entity.Part;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamResDto {
    private Long teamId;
    private String teamName;
    private Part part;
    private List<MemberResDto> members;

    @Builder
    public TeamResDto(Long teamId, String teamName, Part part, List<MemberResDto> members) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.part = part;
        this.members = members;
    }
}
