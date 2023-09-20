package com.example.serversessionjparestful.api;

import com.example.serversessionjparestful.api.dto.response.MemberResDto;
import com.example.serversessionjparestful.application.TeamService;
import com.example.serversessionjparestful.domain.Part;
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
    public ResponseEntity<String> teamCreate(@RequestParam("part") Part part) {
        teamService.teamCreate(part);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @GetMapping("/api/team/list")
    public ResponseEntity<List<MemberResDto>> teamList(@RequestParam("teamId") Long teamId) {
        return new ResponseEntity<>(teamService.teamMemberList(teamId), HttpStatus.OK);
    }
}