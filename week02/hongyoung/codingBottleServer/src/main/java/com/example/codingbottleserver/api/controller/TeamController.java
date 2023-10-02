package com.example.codingbottleserver.api.controller;

import com.example.codingbottleserver.api.dto.request.TeamReqDto;
import com.example.codingbottleserver.api.dto.response.TeamResDto;
import com.example.codingbottleserver.application.service.TeamService;
import com.example.codingbottleserver.domain.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @PostMapping("/teams")
    public ResponseEntity<Team> create(@RequestBody TeamReqDto teamReqDto) {
        Team team = teamService.create(teamReqDto);

        return ResponseEntity.ok(team);
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<TeamResDto> findById(@PathVariable Long id) {
        TeamResDto team = teamService.findById(id);

        return ResponseEntity.ok(team);
    }
}
