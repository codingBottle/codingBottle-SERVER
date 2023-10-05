package com.example.task2.domain.studentinlecture;

import com.example.task2.domain.lecture.Lecture;
import com.example.task2.domain.student.Student;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentInLecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 어떤 학생이 어떤 강의를 듣는지에 대한 정보
    @ManyToOne(fetch = FetchType.EAGER)     // 벌점 정보가 학생 테이블에도 적용되어야 하기 때문에 즉시 로딩 필요
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // 연관 객체가 필요할 때만 EAGER로 가져오게 하는 방법
    // FETCH JOIN

    // EAGER로 깔아둔 다음에, 필요할 때만 LAZY

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", nullable = false)
    private Lecture lecture;

//  벌점을 얼마나 받았는지
    private Integer penalty;

//  Penalty에 있는 값만 허용
    public void setPenalty(Penalty penalty) {
        this.penalty = penalty.getValue();
    }

    @Builder
    private StudentInLecture(Student student, Lecture lecture) {
        this.student = student;
        this.lecture = lecture;
        this.penalty = Penalty.NONE.getValue();
    }
}
