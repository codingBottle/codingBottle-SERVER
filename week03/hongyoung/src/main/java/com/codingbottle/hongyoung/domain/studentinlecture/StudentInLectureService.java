package com.codingbottle.hongyoung.domain.studentinlecture;

import com.codingbottle.hongyoung.domain.lecture.Lecture;
import com.codingbottle.hongyoung.domain.lecture.LectureService;
import com.codingbottle.hongyoung.domain.student.Student;
import com.codingbottle.hongyoung.domain.student.StudentService;
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

        // 3. studentInLecture 객체의 벌점을 변경한다.
        studentInLecture.setPenalty(penalty);

        // 4. 학생의 totalPenalty 를 변경한다.
        Student student = studentInLecture.getStudent();
        int totalPenalty = studentInLectureRepository.sumPenaltyByStudentId(student.getId());
        student.setTotalPenalty(totalPenalty);

        /**
         * 위의 구현한 방법과 아래의 구현 방법 중 어떤게 효율적일까요??!!(의견을 리뷰에 남겨주시면 감사해요...)
         *
         *     // 2. studentInLectureId 라는 경로 변수로 StudentInLecture 객체를 찾아온다.
         *     StudentInLecture studentInLecture = getById(studentInLectureId);
         *
         *     // 3. studentInLecture 객체의 기존 벌점과 새로운 벌점을 비교한다.
         *     Penalty oldPenalty = studentInLecture.getPenalty();
         *
         *     // 4. student 객체의 totalPenalty 도 변경된다.
         *     Student student = studentInLecture.getStudent();
         *
         *     int oldTotalPenalty = student.getTotalPenalty();
         *
         *     // 기존 total penalty에서 기존 penalty를 빼고 새 penalty를 더한다.
         *     int newTotalPenalty = oldTotalPenalty - oldPenalty.getValue() + newPenalty.getValue();
         *
         *     // 5. Update penalties
         *     student.setTotalPenalty(newTotalPenalty);
         *     studentInLecture.setPenalty(newpenaly);
         *
         *     이렇게 구현하면 학생이 수강한 모든 강의에 대해 매번 조회하여 벌점을 다시 계산하는 것보다 효율적일 수도 있을 것 같아요!!
         */

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
