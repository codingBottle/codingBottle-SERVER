package com.example.serversessionjparestful.api;

import com.example.serversessionjparestful.api.dto.request.TeamSaveReqDto;
import com.example.serversessionjparestful.api.dto.response.MemberResDto;
import com.example.serversessionjparestful.application.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> teamCreate(@RequestBody TeamSaveReqDto teamSaveReqDto) {
        teamService.teamCreate(teamSaveReqDto);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<MemberResDto>> teamList(@RequestParam("teamId") Long teamId) {
        return new ResponseEntity<>(teamService.teamMemberList(teamId), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> teamDelete(@RequestParam("teamId") Long teamId) {
        teamService.teamDelete(teamId);
        return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
    }
}