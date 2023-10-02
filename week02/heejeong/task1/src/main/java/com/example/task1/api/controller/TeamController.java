package com.example.task1.api.controller;


import com.example.task1.api.dto.request.TeamSaveReq;
import com.example.task1.api.dto.response.MemberRes;
import com.example.task1.application.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    /*
        팀 생성
     */
    @PostMapping("/teams")
    public ResponseEntity<?> createDto(@RequestBody TeamSaveReq teamReqDto) {
        teamService.teamCreate(teamReqDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("팀이 생성되었습니다.");
    }

    /*
        팀 조회
     */
    @GetMapping("/teams/{teamId}")
    public ResponseEntity<List<MemberRes>> teamList(@PathVariable Long teamId) {
        return new ResponseEntity<>(teamService.teamMemberList(teamId), HttpStatus.OK);
    }
}
