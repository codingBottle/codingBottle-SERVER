package com.example.codingbottleserver.api.dto.request;

import com.example.codingbottleserver.domain.entity.Part;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberReqDto {
    private String name;
    private int age;
    private Part part;

    public MemberReqDto(String name, int age, Part part) {
        this.name = name;
        this.age = age;
        this.part = part;
    }
}
