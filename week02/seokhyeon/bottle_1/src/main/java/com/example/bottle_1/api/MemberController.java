package com.example.bottle_1.api;

import com.example.bottle_1.api.dto.request.BoardSaveReqDto;
import com.example.bottle_1.api.dto.request.MemberSaveReqDto;
import com.example.bottle_1.application.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/member")
    public Long create(@RequestBody MemberSaveReqDto memberSaveReqDto){
        return memberService.create(memberSaveReqDto);
    }

}
