package com.example.codingbottleserver.api.dto.response;

import com.example.codingbottleserver.domain.entity.Member;
import com.example.codingbottleserver.domain.entity.Part;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResDto {
    private Long memberId;
    private String name;
    private int age;
    private Part part;
    private String teamName;

    @Builder
    public MemberResDto(Long memberId, String name, int age, Part part, String teamName) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.part = part;
        this.teamName = teamName;
    }

    public static MemberResDto of(Member member) {
        return MemberResDto.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .age(member.getAge())
                .part(member.getPart())
                .teamName(member.getTeam().getTeamName())
                .build();
    }
}
