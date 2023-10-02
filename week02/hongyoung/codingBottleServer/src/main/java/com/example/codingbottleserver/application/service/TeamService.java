package com.example.codingbottleserver.application.service;

import com.example.codingbottleserver.api.dto.response.MemberResDto;
import com.example.codingbottleserver.api.dto.request.TeamReqDto;
import com.example.codingbottleserver.api.dto.response.TeamResDto;
import com.example.codingbottleserver.domain.entity.Part;
import com.example.codingbottleserver.domain.entity.Team;
import com.example.codingbottleserver.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    @Transactional
    public Team create(TeamReqDto teamReqDto) {
        if (teamRepository.existsByTeamName(teamReqDto.getTeamName())){ // 이미 존재하는 팀 이름인지 확인
            throw new IllegalArgumentException("이미 존재하는 팀 이름입니다.");
        }

        if (Arrays.stream(Part.values())
                .anyMatch(part -> part.name().equals(teamReqDto.getTeamName()))) { // 팀 이름으로 사용할 수 없는 이름인지 확인
            throw new IllegalArgumentException("팀 이름으로 사용할 수 없는 이름입니다.");
        }

        Team team = Team.builder()
                .teamName(teamReqDto.getTeamName())
                .part(teamReqDto.getPart())
                .build();

        return teamRepository.save(team);
    }

    @Transactional(readOnly = true)
    public Team findByName(String teamName) {
        return teamRepository.findByTeamName(teamName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀 이름입니다."));
    }

    @Transactional(readOnly = true)
    public TeamResDto findById(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀입니다."));

        return TeamResDto.builder()
                .teamId(team.getTeamId())
                .teamName(team.getTeamName())
                .part(team.getPart())
                .members(team.getMemberList().stream()
                        .map(MemberResDto::of)
                        .toList())
                .build();

    }
}
