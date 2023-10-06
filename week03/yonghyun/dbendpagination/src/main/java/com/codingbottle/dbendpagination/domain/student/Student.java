package com.codingbottle.dbendpagination.domain.student;

import com.codingbottle.dbendpagination.domain.studentinlecture.Penalty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer totalPenalty;

    @Builder
    private Student(String name) {
        this.name = name;
        totalPenalty = 0;
    }

    public void updateTotalPenalty(Integer penalty){
        totalPenalty += penalty;
    }



}

















