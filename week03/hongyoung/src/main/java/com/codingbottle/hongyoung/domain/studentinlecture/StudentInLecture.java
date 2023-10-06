package com.codingbottle.hongyoung.domain.studentinlecture;

import com.codingbottle.hongyoung.domain.lecture.Lecture;
import com.codingbottle.hongyoung.domain.student.Student;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StudentInLecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 어떤 학생이 어떤 강의를 듣는지에 대한 정보.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // 연관 객체가 필요할 때만 EAGER로 가져오게 하는 방법
    // FETCH JOIN

    // EAGER로 깔아둔 다음에, 필요할 때만 LAZY

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", nullable = false)
    private Lecture lecture;

    private Integer penalty;

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














