package com.example.codingbottleserver.api.controller;

import com.example.codingbottleserver.api.dto.request.MemberReqDto;
import com.example.codingbottleserver.api.dto.response.MemberResDto;
import com.example.codingbottleserver.application.service.MemberService;
import com.example.codingbottleserver.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<Member> save(@RequestBody MemberReqDto memberReqDto) {
        Member member = memberService.save(memberReqDto);

        return ResponseEntity.ok(member);
    }

    @PatchMapping("/members/{id}/teams")
    public ResponseEntity<MemberResDto> saveWithTeam(@PathVariable(value = "id") Long id,
                                             @RequestParam("teamName") String teamName) {
        MemberResDto member = memberService.joinTeam(id, teamName);

        return ResponseEntity.ok(member);
    }
}
