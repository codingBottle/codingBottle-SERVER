package com.codingbottle.hongyoung.global.util;


import com.codingbottle.hongyoung.domain.lecture.Lecture;
import com.codingbottle.hongyoung.domain.lecture.LectureRepository;
import com.codingbottle.hongyoung.domain.student.Student;
import com.codingbottle.hongyoung.domain.student.StudentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Component
public class InitDB {
    private final StudentRepository studentRepository;
    private final LectureRepository lectureRepository;
    // 애플리케이션 실행 시점에 빈 컨테이너에 단 하나의 객체(스프링 빈)을 등록해요

    @Transactional
    @PostConstruct
    public void init() {
        Lecture java = Lecture.builder()
                .name("자바")
                .build();
        Lecture cpp = Lecture.builder()
                .name("C++")
                .build();
        lectureRepository.save(java); lectureRepository.save(cpp);

        Student kim = Student.builder()
                .name("김코딩")
                .build();
        Student jung = Student.builder()
                .name("정코딩")
                .build();

        int studentCount = 100;
        for (int i = 0; i < studentCount; i+=1) {
            Student student = Student.builder()
                    .name(i + "학생")
                    .build();
            studentRepository.save(student);
        }
        studentRepository.save(kim); studentRepository.save(jung);
    }
}
