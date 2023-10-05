package com.codingbottle.dbendpagination.domain.studentinlecture;

import com.codingbottle.dbendpagination.domain.lecture.Lecture;
import com.codingbottle.dbendpagination.domain.lecture.LectureService;
import com.codingbottle.dbendpagination.domain.student.Student;
import com.codingbottle.dbendpagination.domain.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StudentInLectureService {
    private final StudentInLectureRepository studentInLectureRepository;
    private final StudentService studentService;
    private final LectureService lectureService;

    @Transactional
    public long create(Long lectureId, Long studentId) {

        // 1. id로 연관 객체(student, lecture)를 찾는다.
        // Persistence Context (1차 저장소) 에 등록.
        Lecture lecture = lectureService.getById(lectureId);
        Student student = studentService.getById(studentId);

        // 2. 연관 객체를 찾으면 StudentInLecture 객체를 생성하고 저장한다.
        StudentInLecture studentInLecture = StudentInLecture.builder()
                .student(student)
                .lecture(lecture)
                .build();

        // 1차 저장소에 등록
        StudentInLecture savedStuInLec = studentInLectureRepository.save(studentInLecture);
        return savedStuInLec.getId();
        // 메서드를 종료한 직후, 1차 저장소의 변경사항을 DB에 flush 처리하고,
        // 트랜잭션을 commit;
    }

    @Transactional
    public long updatePenalty(Long studentInLectureId, Penalty penalty) {
        // 2. studentInLectureId 라는 경로 변수로 StudentInLecture 객체를 찾아온다.
        StudentInLecture studentInLecture = getById(studentInLectureId);

        // 3. 이전 벌점 정보를 가져온다.
        Penalty previousPenalty = Penalty.NONE; // 기본값으로 설정
        if (studentInLecture.getPenalty() != null) {
            // studentInLecture.getPenalty()의 값이 null이 아닐 때만 변환을 시도하도록 변경
            previousPenalty = Penalty.values()[studentInLecture.getPenalty()];
        }

        // 4. studentInLecture 객체의 벌점을 변경한다.
        studentInLecture.setPenalty(penalty);

        // 5. 학생의 총 벌점을 업데이트한다.
        int updatedTotalPenalty = studentInLecture.getStudent().getTotalPenalty();
        updatedTotalPenalty -= previousPenalty.getValue(); // 이전 벌점 감산
        updatedTotalPenalty += penalty.getValue(); // 새로운 벌점 누적
        studentInLecture.getStudent().setTotalPenalty(updatedTotalPenalty);

        return studentInLecture.getId();
        // 1차 저장소의 정보가 DB로 flush 되고, 트랜잭션이 commit 된다.
    }

    public StudentInLecture getById(Long studentInLectureId) {
        Optional<StudentInLecture> optionalStudentInLecture = studentInLectureRepository.findById(studentInLectureId);
        if (optionalStudentInLecture.isEmpty()) {
            throw new IllegalArgumentException("해당 수강신청이 존재하지 않습니다.");
        }

        return optionalStudentInLecture.get();
    }
}