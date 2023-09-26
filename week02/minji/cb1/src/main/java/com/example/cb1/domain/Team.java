package com.example.cb1.domain;

import  lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Member> memberList = new ArrayList<>();

    @Builder
    private Team(List<Member> memberList) {
        addMemberList(memberList);
    }

    private void addMemberList(List<Member> memberList) {
        this.memberList.addAll(memberList);
    }

}
