//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.codingbottle.dbendpagination.domain.studentinlecture;

import com.codingbottle.dbendpagination.domain.lecture.Lecture;
import com.codingbottle.dbendpagination.domain.lecture.LectureService;
import com.codingbottle.dbendpagination.domain.student.Student;
import com.codingbottle.dbendpagination.domain.student.StudentRepository;
import com.codingbottle.dbendpagination.domain.student.StudentService;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(
        readOnly = true
)
@Service
public class StudentInLectureService {
    private final StudentInLectureRepository studentInLectureRepository;
    private final StudentRepository studentRepository;
    private final StudentService studentService;
    private final LectureService lectureService;

    @Transactional
    public long create(Long lectureId, Long studentId) {
        Lecture lecture = this.lectureService.getById(lectureId);
        Student student = this.studentService.getById(studentId);
        StudentInLecture studentInLecture = StudentInLecture.builder().student(student).lecture(lecture).build();
        StudentInLecture savedStuInLec = (StudentInLecture)this.studentInLectureRepository.save(studentInLecture);
        return savedStuInLec.getId();
    }

    @Transactional
    public long updatePenalty(Long studentInLectureId, Penalty penalty) {
        StudentInLecture studentInLecture = this.getById(studentInLectureId);
        studentInLecture.setPenalty(penalty);
        return studentInLecture.getId();
    }

    @Transactional
    public void sumPenaltyForStudent(Long studentInLectureId) {
        Optional<StudentInLecture> byId = this.studentInLectureRepository.findById(studentInLectureId);
        Student student = ((StudentInLecture)byId.get()).getStudent();
        List<StudentInLecture> studentInLectures = this.studentInLectureRepository.findByStudentId(student.getId());
        int total = 0;

        Integer penalty;
        for(Iterator var6 = studentInLectures.iterator(); var6.hasNext(); total += penalty) {
            StudentInLecture studentInLecture = (StudentInLecture)var6.next();
            penalty = studentInLecture.getPenalty();
        }

        if (student != null) {
            student.setTotalPenalty(total);
        }

    }

    public StudentInLecture getById(Long studentInLectureId) {
        Optional<StudentInLecture> optionalStudentInLecture = this.studentInLectureRepository.findById(studentInLectureId);
        if (optionalStudentInLecture.isEmpty()) {
            throw new IllegalArgumentException("해당 수강신청이 존재하지 않습니다.");
        } else {
            return (StudentInLecture)optionalStudentInLecture.get();
        }
    }

    public StudentInLectureService(final StudentInLectureRepository studentInLectureRepository, final StudentRepository studentRepository, final StudentService studentService, final LectureService lectureService) {
        this.studentInLectureRepository = studentInLectureRepository;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
        this.lectureService = lectureService;
    }
}
