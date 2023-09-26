package com.example.serversessionjparestful.application;

import com.example.serversessionjparestful.api.dto.request.TeamSaveReqDto;
import com.example.serversessionjparestful.api.dto.response.MemberResDto;
import com.example.serversessionjparestful.domain.Member;
import com.example.serversessionjparestful.domain.Team;
import com.example.serversessionjparestful.domain.repository.MemberRepository;
import com.example.serversessionjparestful.domain.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
    public void teamCreate(TeamSaveReqDto teamSaveReqDto) {
        List<Member> memberList = memberRepository.findByPart(teamSaveReqDto.getPart());

        Team team = Team.builder()
                .teamName(teamSaveReqDto.getTeamName())
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

    @Transactional
    public void teamDelete(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow();

        // 팀 객체를 삭제할 때  멤버가 삭제되지 않기 위해서 member의 team값을 null값으로 바꾸고
        for (Member member : team.getMemberList()) {
            member.teamNull();
        }

        //, team객체에서 memberList의 member들을 remove 해줘야한다.
        team.removeMember(team.getMemberList());

        // 그래야 팀 객체만 삭제가 되고, 멤버는 함께 삭제가 되지않음.
        teamRepository.delete(team);
    }

}