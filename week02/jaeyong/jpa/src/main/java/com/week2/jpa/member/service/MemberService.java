package com.week2.jpa.member.service;


import com.week2.jpa.member.dto.MemberResDto;
import com.week2.jpa.member.dto.MemberSaveReqDto;
import com.week2.jpa.member.entity.Member;
import com.week2.jpa.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 멤버 저장.
     */
    @Transactional
    public void save(MemberSaveReqDto memberSaveReqDto) {
        Member member = Member.builder()
                .name(memberSaveReqDto.getName())
                .age(memberSaveReqDto.getAge())
                .part(memberSaveReqDto.getPart())
                .build();

        memberRepository.save(member);
    }

    // 멤버 한명 삭제
    @Transactional
    public void oneDelete(Long memberId) {
        memberRepository.delete(memberRepository.findById(memberId).orElseThrow());
    }

    @Transactional
    // 멤버 모두 삭제
    public void allDelete() {

    }

    // 멤버 한명 찾기
    public MemberResDto findOne(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();

        return new MemberResDto(
                member.getMemberId(),
                member.getName(),
                member.getAge(),
                member.getPart());
    }

    // 멤버 나이에 따라서 모두 조회 -> 정렬 + 응용
    public List<MemberResDto> findAllByAge() {
        List<Member> memberList = memberRepository.findAllByAge(22, Sort.by(Sort.Direction.DESC, "memberId"));

        List<MemberResDto> memberResDtoList = new ArrayList<>();
        for (Member member : memberList) {
            MemberResDto memberResDto = new MemberResDto(
                    member.getMemberId(),
                    member.getName(),
                    member.getAge(),
                    member.getPart());

            memberResDtoList.add(memberResDto);
        }

        return memberResDtoList;
    }

    // 페이징
    public Page<MemberResDto> pageFindAll(int page, int size) {
        Page<Member> memberPage = memberRepository.findAll(PageRequest.of(page, size));

        return memberPage.map(this::mapToMember);
    }

    private MemberResDto mapToMember(Member member) {
        return new MemberResDto(member.getMemberId(),
                member.getName(),
                member.getAge(),
                member.getPart());
    }

    // 멤버 이름으로 검색해서 찾기
    public List<MemberResDto> findNameMember(String search) {
        List<Member> searchNameMember = memberRepository.findByNameContaining(search);

        List<MemberResDto> memberResDtoList = new ArrayList<>();
        for (Member member : searchNameMember) {
            MemberResDto memberResDto = new MemberResDto(
                    member.getMemberId(),
                    member.getName(),
                    member.getAge(),
                    member.getPart());

            memberResDtoList.add(memberResDto);
        }

        return memberResDtoList;
    }

}
