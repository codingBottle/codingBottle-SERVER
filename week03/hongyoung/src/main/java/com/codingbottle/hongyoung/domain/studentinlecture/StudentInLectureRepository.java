package com.codingbottle.hongyoung.domain.studentinlecture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentInLectureRepository extends JpaRepository<StudentInLecture, Long> {
    @Query("select sum(s.penalty) from StudentInLecture s where s.student.id = :studentId")
    int sumPenaltyByStudentId(Long studentId);
}