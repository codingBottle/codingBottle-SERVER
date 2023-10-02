package com.example.serversessionjparestful.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    private String name;

    private int age;

    @Enumerated(EnumType.STRING)
    private Part part;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;


    @Builder
    private Member(String name, int age, Part part) {
        this.name = name;
        this.age = age;
        this.part = part;
    }

    public void teamSetting(Team team) {
        this.team = team;
    }

    public void teamNull() {
        this.team = null;
    }
}