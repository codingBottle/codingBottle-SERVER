package com.example.cb1.application;


import com.example.cb1.api.dto.MemberResDto;
import com.example.cb1.domain.Member;
import com.example.cb1.domain.Part;
import com.example.cb1.domain.Team;
import com.example.cb1.domain.repository.MemberRepository;
import com.example.cb1.domain.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TeamService {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    public TeamService(TeamRepository teamRepository, MemberRepository memberRepository) {
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;
    }

    // 파트별 팀 생성 
    @Transactional
    public void teamCreate(Part part) {
        List<Member> memberList = memberRepository.findByPart(part);

        Team team = Team.builder()
                .memberList(memberList)
                .build();

        // 멤버의 team 값을 null에서 생성된 팀으로 설정
        for (Member member : memberList) {
            member.teamSetting(team);
        }

        teamRepository.save(team);
    }

    public List<MemberResDto> teamMemberList(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow();
        List<Member> memberList = team.getMemberList();

        List<MemberResDto> memberResDtoList = new ArrayList<>();
        for (Member member : memberList) {
            MemberResDto memberResDto = new MemberResDto(
                    member.getMemberId(),
                    member.getName(),
                    member.getAge(),
                    member.getPart());

            memberResDtoList.add(memberResDto);
        }

        return memberResDtoList;
    }

}