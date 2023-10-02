package com.codingbottle.dbendpagination.domain.studentinlecture;

import com.codingbottle.dbendpagination.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentInLectureRepository extends JpaRepository<StudentInLecture, Long> {
    List<StudentInLecture> findByStudentId(Long studentInLectureId);
}