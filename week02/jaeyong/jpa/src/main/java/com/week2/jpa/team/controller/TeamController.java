package com.week2.jpa.team.controller;

import com.week2.jpa.member.dto.MemberResDto;
import com.week2.jpa.member.entity.Member;
import com.week2.jpa.team.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/api/team/create")
    public ResponseEntity<String> teamCreate(@RequestParam("part") Member.Part part,
                                             @RequestParam("teamName") String teamName) {
        teamService.teamCreate(part, teamName);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @GetMapping("/api/team/list")
    public ResponseEntity<List<MemberResDto>> teamList(@RequestParam("teamId") Long teamId) {
        return new ResponseEntity<>(teamService.teamMemberList(teamId), HttpStatus.OK);
    }
}
