package com.example.task2.domain.studentinlecture;

import com.example.task2.domain.lecture.Lecture;
import com.example.task2.domain.lecture.LectureService;
import com.example.task2.domain.student.Student;
import com.example.task2.domain.student.StudentService;
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
    private final StudentService studentService;
    private final LectureService lectureService;

    @Transactional
    public long create(Long lectureId, Long studentId) {

//      id로 연관 객체(student, lecture)를 찾기
//      Persistence Context (1차 저장소) 에 등록
        Lecture lecture = lectureService.getById(lectureId);
        Student student = studentService.getById(studentId);

//      연관 객체를 찾으면 StudentInLecture 객체를 생성하고 저장
        StudentInLecture studentInLecture = StudentInLecture.builder()
                .student(student)
                .lecture(lecture)
                .build();

//      1차 저장소에 등록
        StudentInLecture savedStuInLec = studentInLectureRepository.save(studentInLecture);

        return savedStuInLec.getId();
    }

    @Transactional
    public long updatePenalty(Long studentInLectureId, Penalty penalty) {
        
//      studentInLectureId 라는 경로 변수로 StudentInLecture 객체를 찾아옴
        StudentInLecture studentInLecture = getById(studentInLectureId);

//      studentInLecture 객체의 벌점 변경
        studentInLecture.setPenalty(penalty);
        
//      학생 별 벌점

        return studentInLecture.getId();
    }

//  벌점 점수 합산
    @Transactional
    public void getTotalPenalty(Long studentInLectureId) {

//      수강신청 내역의 학생
//      studentInLectureId의 값이 not null이기 때문에 한번에 Entity를 가져옴
        Student students = studentInLectureRepository.findById(studentInLectureId).get().getStudent();

//      해당 학생의 모든 수강신청
        List<StudentInLecture> stuInLecs = studentInLectureRepository.findByStudentId(students.getId());

//      벌점 합산
        int totalPenalty = stuInLecs.stream()
                .mapToInt(StudentInLecture::getPenalty)
                .sum();

//      student Entity에 총 벌점 적용
        students.setTotalPenalty(totalPenalty);
    }

    public StudentInLecture getById(Long studentInLectureId) {

        Optional<StudentInLecture> optionalStudentInLecture = studentInLectureRepository.findById(studentInLectureId);

        if (optionalStudentInLecture.isEmpty()) {
            throw new IllegalArgumentException("해당 수강신청이 존재하지 않습니다.");
        }

        return optionalStudentInLecture.get();

//      return studentInLectureRepository.findById(studentInLectureId).orElseThrow(() -> new IllegalArgumentException("해당 수강신청이 존재하지 않습니다.");
    }
}