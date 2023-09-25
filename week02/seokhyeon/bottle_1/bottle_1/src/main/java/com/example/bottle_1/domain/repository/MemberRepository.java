package com.example.bottle_1.domain.repository;

import com.example.bottle_1.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
