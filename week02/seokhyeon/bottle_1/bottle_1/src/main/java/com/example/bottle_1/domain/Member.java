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
    //board의 값을 넣어주진 않기 때문에 추가하는 메소드를 만들어 줘야한다 안만들면 이곳에는 null값이 들어간다.

    @Builder
    public Member(String nickname,int age, boolean admin){
        this.nickname = nickname;
        this.age = age;
        this.admin = admin;

    }

    //@ManyToOne에는 mappedBy 옵션이 없기 때문에 @OneToMany가 관계의 주인일 경우 양방향 매핑이 불가능하다. ( = @ManyToOne은 항상 주인이 된다.

}
