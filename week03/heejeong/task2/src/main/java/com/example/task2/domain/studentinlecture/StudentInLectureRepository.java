package com.example.task2.domain.studentinlecture;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentInLectureRepository extends JpaRepository<StudentInLecture, Long> {
    List<StudentInLecture> findByStudentId(Long studentId);
}
