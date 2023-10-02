package com.week2.jpa.team.entity;

import com.week2.jpa.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;

    @Column
    private String teamName;

    // 팀 삭제 시 팀원들도 같이 삭제 되는가?
    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Member> memberList = new ArrayList<>();

    @Builder
    private Team(List<Member> memberList, String teamName) {
        this.teamName = teamName;
        addMemberList(memberList);
    }

    private void addMemberList(List<Member> memberList) {
        this.memberList.addAll(memberList);
    }
}
