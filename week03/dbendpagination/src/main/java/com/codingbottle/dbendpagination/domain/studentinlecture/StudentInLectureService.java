package com.codingbottle.dbendpagination.domain.studentinlecture;

import com.codingbottle.dbendpagination.domain.lecture.Lecture;
import com.codingbottle.dbendpagination.domain.lecture.LectureService;
import com.codingbottle.dbendpagination.domain.student.Student;
import com.codingbottle.dbendpagination.domain.student.StudentRepository;
import com.codingbottle.dbendpagination.domain.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StudentInLectureService {
    private final StudentInLectureRepository studentInLectureRepository;

    private final StudentRepository studentRepository;
    private final StudentService studentService;
    private final LectureService lectureService;

    @Transactional
    public long create(Long lectureId, Long studentId) {

        // 1. id로 연관 객체(student, lecture)를 찾는다.
        // Persistence Context (1차 저장소) 에 등록. (Transaction)
        Lecture lecture = lectureService.getById(lectureId);
        Student student = studentService.getById(studentId);

        // 2. 연관 객체를 찾으면 StudentInLecture 객체를 생성하고 저장한다.
        StudentInLecture studentInLecture = StudentInLecture.builder()
                .student(student)
                .lecture(lecture)
                .build();

        // 1차 저장소에 등록 (Transaction)
        //객체 만들고 id반환
        StudentInLecture savedStuInLec = studentInLectureRepository.save(studentInLecture);
        return savedStuInLec.getId();
        // 메서드를 종료한 직후, 1차 저장소의 변경사항을 DB에 flush 처리하고, (Transaction)
        // 트랜잭션을 commit;
    }

    @Transactional
    public long updatePenalty(Long studentInLectureId, Penalty penalty) {
        // 2. studentInLectureId 라는 경로 변수로 StudentInLecture 객체를 찾아온다.
        StudentInLecture studentInLecture = getById(studentInLectureId);

        // 3. studentInLecture 객체의 벌점을 변경한다.
        studentInLecture.setPenalty(penalty);

        return studentInLecture.getId();
        // 1차 저장소의 정보가 DB로 flush(반영) 되고, 트랜잭션이 commit 된다.
    }

    @Transactional
    public void sumPenaltyForStudent(Long studentInLectureId) {
        //findbyid가 jpa에 만들어져 있다. 반환형이 optional로 정해져 있따.
        Optional<StudentInLecture> byId = studentInLectureRepository.findById(studentInLectureId);

        //학생 엔티티를 가져옴
        Student student = byId.get().getStudent();

        // 해당 학생의 모든 수강신청 내역을 가져옴
        List<StudentInLecture> studentInLectures = studentInLectureRepository.findByStudentId(student.getId());

        // 벌점을 합산할 변수를 초기화
        int total = 0;

        // 각 수강신청 내역의 벌점 합산
        for (StudentInLecture studentInLecture : studentInLectures) {
            Integer penalty = studentInLecture.getPenalty();
//            if (penalty != null) {
            total += penalty;
//            }

        }
        if (student != null) {
            // 학생 엔티티에 벌점을 업데이트
            student.setTotalPenalty(total);
        }
    }


    public StudentInLecture getById(Long studentInLectureId) {
        Optional<StudentInLecture> optionalStudentInLecture = studentInLectureRepository.findById(studentInLectureId);
        if (optionalStudentInLecture.isEmpty()) {
            throw new IllegalArgumentException("해당 수강신청이 존재하지 않습니다.");
        }

        return optionalStudentInLecture.get();
    }


}
