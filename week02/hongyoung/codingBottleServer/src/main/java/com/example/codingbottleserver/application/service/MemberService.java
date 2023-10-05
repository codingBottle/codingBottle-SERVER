package com.example.codingbottleserver.application.service;

import com.example.codingbottleserver.api.dto.request.MemberReqDto;
import com.example.codingbottleserver.api.dto.response.MemberResDto;
import com.example.codingbottleserver.domain.entity.Member;
import com.example.codingbottleserver.domain.entity.Team;
import com.example.codingbottleserver.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final TeamService teamService;

    @Transactional
    public Member save(MemberReqDto memberReqDto){
        Member member = Member.builder()
                .age(memberReqDto.getAge())
                .name(memberReqDto.getName())
                .part(memberReqDto.getPart())
                .build();

        return memberRepository.save(member);
    }

    @Transactional
    public MemberResDto joinTeam(Long id, String teamName) {
        Member member = findById(id);

        if(member.getTeam() != null) {
            throw new IllegalArgumentException("이미 팀에 속해있는 회원입니다.");
        }

        Team team = teamService.findByName(teamName);

        member.joinTeam(team);

        return MemberResDto.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .age(member.getAge())
                .part(member.getPart())
                .teamName(member.getTeam().getTeamName())
                .build();
    }

    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }
}
