package com.example.task1.application;


import com.example.task1.api.dto.request.MemberSaveReq;
import com.example.task1.api.dto.response.MemberRes;
import com.example.task1.domain.Member;
import com.example.task1.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {


    private final MemberRepository memberRepository;

    //  멤버 저장
    @Transactional
    public void save(MemberSaveReq memberSaveReqDto) {
        Member member = Member.builder()
                .name(memberSaveReqDto.getName())
                .age(memberSaveReqDto.getAge())
                .part(memberSaveReqDto.getPart())
                .build();

        memberRepository.save(member);
    }

    //  멤버 모두 삭제
    @Transactional
    public void allDelete() {

    }

    //  멤버 1명 찾기
    public MemberRes findOne(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();

        return new MemberRes(
                member.getMemberId(),
                member.getName(),
                member.getAge(),
                member.getPart()
        );
    }

    // 멤버 한명 삭제
    @Transactional
    public void oneDelete(Long memberId) {
        memberRepository.delete(memberRepository.findById(memberId).orElseThrow());
    }

    // 멤버 나이에 따라서 모두 조회 -> 정렬 + 응용
    public List<MemberRes> findAllByAge() {
        List<Member> memberList = memberRepository.findAllByAge(22, Sort.by(Sort.Direction.DESC, "memberId"));

        List<MemberRes> memberResDtoList = new ArrayList<>();
        for (Member member : memberList) {
            MemberRes memberResDto = new MemberRes(
                    member.getMemberId(),
                    member.getName(),
                    member.getAge(),
                    member.getPart());

            memberResDtoList.add(memberResDto);
        }

        return memberResDtoList;
    }
}

