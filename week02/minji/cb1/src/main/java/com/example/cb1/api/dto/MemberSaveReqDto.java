package com.example.cb1.api.dto;

import com.example.cb1.domain.Part;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSaveReqDto {

    public String name;

    public int age;

    public Part part;

    public MemberSaveReqDto(String name, int age, Part part) {
        this.name = name;
        this.age = age;
        this.part = part;
    }

}
