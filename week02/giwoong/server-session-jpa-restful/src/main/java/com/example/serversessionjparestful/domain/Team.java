package com.example.serversessionjparestful.domain;

import lombok.AccessLevel;
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

    private String teamName;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Member> memberList = new ArrayList<>();

    @Builder
    private Team(String teamName, List<Member> memberList) {
        this.teamName = teamName;
        addMemberList(memberList);
    }

    private void addMemberList(List<Member> memberList) {
        this.memberList.addAll(memberList);
    }

    public void removeMember(List<Member> memberList) {
        this.memberList.removeAll(memberList);
    }

}