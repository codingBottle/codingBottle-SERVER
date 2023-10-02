package com.example.task1.application;


import com.example.task1.api.dto.request.TeamSaveReq;
import com.example.task1.api.dto.response.MemberRes;
import com.example.task1.domain.Member;
import com.example.task1.domain.Team;
import com.example.task1.domain.repository.MemberRepository;
import com.example.task1.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    //  파트 별 팀 생성
    @Transactional
    public void teamCreate(TeamSaveReq teamSaveReq) {
        List<Member> members = memberRepository.findByPart(teamSaveReq.getPart());   // 파트 별 멤버 객체

//      팀 객체 생성
        Team team = Team.builder()
                .teamName(teamSaveReq.getTeamName())
                .memberList(members)
                .build();

//      해당 멤버의 팀 속성을 새로 생성한 team 객체로 설정
        for (Member member: members) {
            member.teamSetting(team);
        }

        teamRepository.save(team);
    }

    //  팀 목록 조회
    public List<MemberRes> teamMemberList(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow();
        List<Member> members = team.getMemberList();

        List<MemberRes> memberResDtoList = new ArrayList<>();
        for (Member member: members) {
            MemberRes memberResDto = new MemberRes(
                    member.getMemberId(),
                    member.getName(),
                    member.getAge(),
                    member.getPart()
            );

            memberResDtoList.add(memberResDto);
        }

        return memberResDtoList;
    }
}
