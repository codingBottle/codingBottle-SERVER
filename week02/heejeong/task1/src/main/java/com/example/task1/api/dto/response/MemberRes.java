package com.example.task1.api.dto.response;

import com.example.task1.domain.Part;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRes {

    private Long memberId;

    private String name;

    private int age;

    private Part part;

    public MemberRes(Long memberId, String name, int age, Part part) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.part = part;
    }
}
