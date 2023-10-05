package com.example.task2.global.util;

import com.example.task2.domain.lecture.Lecture;
import com.example.task2.domain.lecture.LectureRepository;
import com.example.task2.domain.student.Student;
import com.example.task2.domain.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component  // 애플리케이션이 실행되면 자동으로 테이블이 생성되게 함
@RequiredArgsConstructor
public class InitDB {

    private final StudentRepository studentRepository;
    private final LectureRepository lectureRepository;
    
    // 애플리케이션 실행 시점에 빈 컨테이너에 단 하나의 객체(스프링 빈)을 등록

    @Transactional
    @PostConstruct  // 컨테이너 등록 후 실행됨
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
        studentRepository.save(kim); studentRepository.save(jung);

//      페이지네이션을 위한 코드
        int studentCount = 100;
        for (int i = 0; i < studentCount; i+=1) {
            Student student = Student.builder()
                    .name(i + "학생")
                    .build();
            studentRepository.save(student);
        }
    }
}
