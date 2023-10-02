package com.example.codingbottleserver.domain.repository;

import com.example.codingbottleserver.domain.entity.Team;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Boolean existsByTeamName(String teamName);

    Optional<Team> findByTeamName(String teamName);
}
