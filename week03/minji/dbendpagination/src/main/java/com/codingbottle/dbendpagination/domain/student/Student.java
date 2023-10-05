package com.codingbottle.dbendpagination.domain.student;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    //왜 이 컬럼에는 not null이 안될까요? 이 어노테이션을 넣으면 error발생..
    //@Column(nullable = false)
    private Integer totalPenalty;

    @Builder
    private Student(String name) {
        this.name = name;
    }



}

















