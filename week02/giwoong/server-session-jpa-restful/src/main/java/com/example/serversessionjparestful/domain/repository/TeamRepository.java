package com.example.serversessionjparestful.domain.repository;

import com.example.serversessionjparestful.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    // N+1 문제를 해결하기 위한 JOIN FETCH
    @Query("SELECT t " +
            "FROM Team t " +
            "JOIN FETCH t.memberList")
    Set<Team> findAllJoinFetch();

}