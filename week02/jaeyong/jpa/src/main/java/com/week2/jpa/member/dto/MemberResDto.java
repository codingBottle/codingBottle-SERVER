package com.week2.jpa.member.dto;


import com.week2.jpa.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResDto {
    private Long memberId;
    private String name;
    private int age;
    private Member.Part part;

    public MemberResDto(Long memberId, String name, int age, Member.Part part) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.part = part;
    }
}