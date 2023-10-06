package com.codingbottle.hongyoung.domain.student;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


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
    private int totalPenalty;

    @Builder
    private Student(String name) {
        this.name = name;
        this.totalPenalty = 0;
    }

    public void setTotalPenalty(int totalPenalty) {
        this.totalPenalty = totalPenalty;
    }
}

















