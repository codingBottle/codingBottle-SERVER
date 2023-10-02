package com.week2.jpa.team.service;


import com.week2.jpa.member.dto.MemberResDto;
import com.week2.jpa.member.entity.Member;
import com.week2.jpa.member.mapper.MemberMapper;
import com.week2.jpa.member.repository.MemberRepository;
import com.week2.jpa.team.entity.Team;
import com.week2.jpa.team.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TeamService {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public TeamService(TeamRepository teamRepository, MemberRepository memberRepository, MemberMapper memberMapper) {
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    // 파트별 팀 생성
    @Transactional
    public void teamCreate(Member.Part part, String teamName) {
        List<Member> memberList = memberRepository.findByPart(part);

        teamVerification(teamName, part);

        Team team = Team.builder()
                .teamName(teamName)
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

        List<MemberResDto> memberResDtoList = memberMapper.MembersToMemberResDtos(memberList);

        return memberResDtoList;
    }

    public void teamVerification(String teamName, Member.Part part) {
        if(teamRepository.existsByTeamName(teamName))
            throw new RuntimeException("이미 존재하는 팀명입니다.");
        else if (teamName.equals(part.name()))
            throw new RuntimeException("파트와 팀 이름은 같을 수 없습니다.");
    }
}
