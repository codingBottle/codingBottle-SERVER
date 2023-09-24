package com.example.bottle_1.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 만듬
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 생성 db에 위임
    @Column(name = "member_id")
    private Long memberId;

    private String nickname;

    private int age;

    private boolean admin;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Board> boardList = new ArrayList<>(); //멤버 한명이 여러개의 board가짐

    @Builder
    public Member(String nickname,int age, boolean admin){
        this.nickname = nickname;
        this.age = age;
        this.admin = admin;

    }


}
