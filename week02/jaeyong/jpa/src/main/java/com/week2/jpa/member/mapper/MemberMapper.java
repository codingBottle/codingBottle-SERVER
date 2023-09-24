package com.week2.jpa.member.mapper;

import com.week2.jpa.member.dto.MemberResDto;
import com.week2.jpa.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    List<MemberResDto> MembersToMemberResDtos(List<Member> members);
}
