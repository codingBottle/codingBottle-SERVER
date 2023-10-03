//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.codingbottle.dbendpagination.domain.studentinlecture;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentInLectureRepository extends JpaRepository<StudentInLecture, Long> {
    List<StudentInLecture> findByStudentId(Long studentInLectureId);
}
