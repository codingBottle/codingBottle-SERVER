package com.example.task1.api.controller;


import com.example.task1.api.common.RspTemple;
import com.example.task1.api.dto.request.BoardSaveReq;
import com.example.task1.api.dto.request.MemberSaveReq;
import com.example.task1.api.dto.response.MemberRes;
import com.example.task1.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /*
        멤버 저장
     */
    @PostMapping("api/save")
    public ResponseEntity<String> save(@RequestBody MemberSaveReq memberSaveReq) {
        memberService.save(memberSaveReq);

        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    /*
     * 멤버 한명 삭제
     */
    @PostMapping("/api/delete")
    public ResponseEntity<String> delete(@RequestParam("memberId") Long memberId) {
        memberService.oneDelete(memberId);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    /*
     * 멤버 나이에 따라 모두 조회
     */
    @GetMapping("/api/list")
    public ResponseEntity<List<MemberRes>> list() {
        return new ResponseEntity<>(memberService.findAllByAge(), HttpStatus.OK);
    }

}
