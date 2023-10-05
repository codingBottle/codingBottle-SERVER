package com.example.task1.api.dto.request;

import com.example.task1.domain.Part;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
    멤버 저장 시 사용할 DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSaveReq {

    private String name;

    private int age;

    private Part part;

    public MemberSaveReq(String name, int age, Part part) {
        this.name = name;
        this.age = age;
        this.part = part;
    }
}
