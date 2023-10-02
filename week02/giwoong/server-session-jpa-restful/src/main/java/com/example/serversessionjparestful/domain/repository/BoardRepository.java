package com.example.serversessionjparestful.domain.repository;

import com.example.serversessionjparestful.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}