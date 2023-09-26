package com.example.task1.domain.repository;

import com.example.task1.domain.Member;
import com.example.task1.domain.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    //  예외 처리 (이름이 존재하는지)
    boolean existsByName(String name);

    //  %이름% 키워드 찾기
    List<Member> findByNameContaining(String search);

    //  파트 별로 멤버 찾기
    List<Member> findByPart(Part part);

    // 나이별 정렬
    List<Member> findAllByAge(int age, Sort sort);
}
