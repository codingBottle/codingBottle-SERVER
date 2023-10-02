package com.example.cb1.api.dto;


import com.example.cb1.domain.Part;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResDto {
    private Long memberId;
    private String name;
    private int age;
    private Part part;

    public MemberResDto(Long memberId, String name, int age, Part part) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.part = part;
    }
}
