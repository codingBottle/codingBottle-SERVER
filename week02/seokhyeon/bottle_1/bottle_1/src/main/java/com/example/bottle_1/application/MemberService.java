package com.example.bottle_1.application;

import com.example.bottle_1.api.dto.request.BoardSaveReqDto;
import com.example.bottle_1.api.dto.request.MemberSaveReqDto;
import com.example.bottle_1.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long create(MemberSaveReqDto memberSaveReqDto) {
        return memberRepository.save(memberSaveReqDto.toEntity()).getMemberId();
    }
}
