package com.example.cb1.domain.repository;

import com.example.cb1.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // 추가적인 검색 또는 쿼리 메서드를 선언할 수 있습니다.
}