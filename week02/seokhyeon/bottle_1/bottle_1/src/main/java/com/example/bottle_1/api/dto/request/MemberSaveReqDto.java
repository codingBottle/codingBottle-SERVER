package com.example.bottle_1.api.dto.request;


import com.example.bottle_1.domain.Board;
import com.example.bottle_1.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSaveReqDto {
    private String nickname;

    private int age;

    private boolean admin;

    public MemberSaveReqDto(String nickname, int age, boolean admin) {
        this.nickname = nickname;
        this.age = age;
        this.admin = admin;
    }

    public Member toEntity() {
        return Member.builder()
                .nickname(nickname)
                .age(age)
                .admin(admin)
                .build();
    }
}
