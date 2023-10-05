package com.example.bottle_1.domain.repository;

import com.example.bottle_1.domain.Board;
import com.example.bottle_1.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByMember(Member member);

}
