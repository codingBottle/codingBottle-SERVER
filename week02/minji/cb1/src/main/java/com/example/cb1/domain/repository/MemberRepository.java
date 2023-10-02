package com.example.cb1.domain.repository;

import com.example.cb1.domain.Member;
import com.example.cb1.domain.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 나이가 20세 이상 멤버 모두 찾기
    @Query("select m " +
            "from Member m " +
            "where m.age > 20 ")
    List<Member> findAll();

    // 이름으로 멤버찾기
    Optional<Member> findByName(String name);

    // 나이로 멤버 찾기
    List<Member> findByAge(int age);

    // 파트로 멤버찾기
    List<Member> findByPart(Part part);

    // 나이별 정렬
    List<Member> findAllByAge(int age, Sort sort);

    // 페이징
    Page<Member> findAll(Pageable pageable);

    // 멤버 아이디가 존재하는지
    boolean existsByMemberId(Long memberId);

    // 멤버 이름이 존재하는지
    boolean existsByName(String name);

    // 멤버 아이디 And 이름이 일치하는지
    boolean existsByMemberIdAndName(Long memberId, String name);

    // 키워드% 찾기
    List<Member> findByNameStartingWith(String search);

    // %키워드 찾기
    List<Member> findByNameEndingWith(String search);

    // %키워드% 찾기
    List<Member> findByNameContaining(String search);


}
