package com.example.serversessionjparestful.api;

import com.example.serversessionjparestful.api.dto.request.MemberSaveReqDto;
import com.example.serversessionjparestful.api.dto.response.MemberResDto;
import com.example.serversessionjparestful.application.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 멤버 저장
     */
    @PostMapping("/api/save")
    public ResponseEntity<String> save(@RequestBody MemberSaveReqDto memberSaveReqDto) {
        memberService.save(memberSaveReqDto);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    /**
     * 멤버 한명 삭제
     */
    @PostMapping("/api/delete")
    public ResponseEntity<String> delete(@RequestParam("memberId") Long memberId) {
        memberService.oneDelete(memberId);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    /**
     * 멤버 모두 삭제
     */


    /**
     * 멤버 한명 찾기
     */
    @GetMapping("/api/find-one")
    public ResponseEntity<MemberResDto> findOne(@RequestParam("memberId") Long memberId) {
        return new ResponseEntity<>(memberService.findOne(memberId), HttpStatus.OK);
    }

    /**
     * 멤버 나이에 따라 모두 조회
     */
    @GetMapping("/api/list")
    public ResponseEntity<List<MemberResDto>> list() {
        return new ResponseEntity<>(memberService.findAllByAge(), HttpStatus.OK);
    }

    /**
     * 페이징
     */
    @GetMapping("/api/page-list")
    public ResponseEntity<Page<MemberResDto>> pageList(@RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size) {
        return new ResponseEntity<>(memberService.pageFindAll(page, size), HttpStatus.OK);
    }

    /**
     *
     */
    @PostMapping("/api/search")
    public ResponseEntity<List<MemberResDto>> searchNameMemberList(@RequestParam("search") String search) {
        return new ResponseEntity<>(memberService.findNameMember(search), HttpStatus.OK);
    }

}