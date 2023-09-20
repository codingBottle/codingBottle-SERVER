package com.example.serversessionjparestful.api.dto.request;

import com.example.serversessionjparestful.domain.Part;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSaveReqDto {

    private String name;
    private int age;
    private Part part;

    public MemberSaveReqDto(String name, int age, Part part) {
        this.name = name;
        this.age = age;
        this.part = part;
    }
}