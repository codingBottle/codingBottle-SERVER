package com.codingbottle.dbendpagination.domain.student;

import com.codingbottle.dbendpagination.domain.studentinlecture.Penalty;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Setter
    private Integer totalPenalty;

    @Builder
    private Student(String name, Integer totalPenalty) {
        this.name = name;
        this.totalPenalty = totalPenalty != null ? totalPenalty : 0;
    }

}

















