package com.example.task2.domain.student;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    
    @Setter // 벌점을 변경해야 하므로 setter 필요
    @Column(nullable = false)
    private int totalPenalty;   // 벌점 합계

//  id를 생성자에 넣어두면 에러
//    @Builder    // 빌더 패턴 구현
//    private Student(String name) {
//        this.name = name;
//    }

    @Builder
    public Student(String name, int totalPenalty) {
        this.name = name;
        this.totalPenalty = totalPenalty;
    }
}
