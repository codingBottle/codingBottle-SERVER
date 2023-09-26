package com.example.cb1.api;


import com.example.cb1.api.dto.MemberResDto;
import com.example.cb1.application.TeamService;
import com.example.cb1.domain.Part;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/api/team/create")
    public ResponseEntity<String> teamCreate(@RequestParam("part") Part part) {
        teamService.teamCreate(part);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @GetMapping("/api/team/list")
    public ResponseEntity<List<MemberResDto>> teamList(@RequestParam("teamId") Long teamId) {
        return new ResponseEntity<>(teamService.teamMemberList(teamId), HttpStatus.OK);
    }
}
