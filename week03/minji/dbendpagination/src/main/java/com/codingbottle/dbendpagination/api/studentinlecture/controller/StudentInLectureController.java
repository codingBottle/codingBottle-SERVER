package com.codingbottle.dbendpagination.api.studentinlecture.controller;

import com.codingbottle.dbendpagination.api.common.RspTemplate;
import com.codingbottle.dbendpagination.api.studentinlecture.dto.PenaltyReqDto;
import com.codingbottle.dbendpagination.domain.studentinlecture.Penalty;
import com.codingbottle.dbendpagination.domain.studentinlecture.StudentInLecture;
import com.codingbottle.dbendpagination.domain.studentinlecture.StudentInLectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/** // 백엔드 애플리케이션 개발을 위한 최소 역량 ?
 * // 우리의 취업전선 경쟁자는 스스로 공부를 하는 사람들!!!
 * 
 * // '스프링 부트' 라는 이름이 있는 책이나 강의 사서 배우면 좋아요.
 *
 * 1. 스프링 기초 이론 (의존성 주입, 빈 컨테이너(IOC Container), thread per request)
 * 2. DB 기초 이론 (적어도 간단한 join문 작성 가능하고, 트랜잭션이 왜 존재하는 건지 알고 있음)
 * 3. JPA 이론 (persistence context 의 1차 캐시, 스냅샷, DB flush 타이밍 등의 개념 'JPA 김영한씨 책' )
 * 4. HTTP 기초 (HTTP Method들의 의미, 자주 사용되는 상태코드와 그것의 의미, 자주 사용되는 HTTP Header의 사용예시, SOP-CORS 이해)
 * 5. 개발 프로세스에 대한 경험과 감각 (언어 숙련도, 가독성-재사용성 좋은 코드 짜기, 빈틈없는 코드를 짜고 테스트 잘 하기)
 */
@RequiredArgsConstructor
@RestController
public class StudentInLectureController {
    private final StudentInLectureService studentInLectureService;

    /**
     * 1. 수강신청 API 만들기
     * url - 계층구조.
     * url - [POST] /student-in-lectures/lectures/1/students/1
     * <p>
     * 그럼 StudentInLecture 객체 생성해서 저장 가능
     * penaltyState는 NONE
     */
    // 수강신청
    // studentId는 사실 일반적인 경우 넣을 필요가 없는데
    // 아직 저희가 '인증' 을 배우지 않아서 그러함.
    @PostMapping("/student-in-lectures/lectures/{lectureId}/students/{studentId}")
    // handler method
    public RspTemplate<Void> handleCreateStudentInLecture(
            @PathVariable Long lectureId, @PathVariable Long studentId
    ) {
        // 수강신청 객체 StudentInLecture를 생성해서 repo 객체의 save() 호출에서 저장하는 것이 목적.
        // lectureId, studentId 로 강의와 학생을 파악 가능.
        // service 계층을 호출해서 객체 생성.
        long savedStuInLecId = studentInLectureService.create(lectureId, studentId);

        return new RspTemplate<>(HttpStatus.OK
                , savedStuInLecId + "번 수강신청 완료"
        );
//        return ResponseEntity
//                // "/student-in-lectures/{savedStuInLecId}" 로 [GET] 요청을 보내면 방금 만든 데이터 보내준다는 의미.
//                .created(URI.create("/student-in-lectures/" + savedStuInLecId))
//                .build();
    }

    /**
     * 2. 벌점부여 API 만들기
     * 원데이 클래스라고 가정함
     * n주차 이런 거 없음
     * n주차를 구현하려면 테이블을 하나 더 만들었을 것 같음
     * <p>
     * url : /student-in-lecture/1 // 여기서 이미 어떤 강의 어떤 학생인지가 정해져있음.
     * PATCH PUT
     * body: 벌점(지각, 결석 ENUM)
     */

    //수정 전 벌점 api
//    @PatchMapping("/student-in-lectures/{studentInLectureId}")
//    public RspTemplate<Void> handleUpdateStudentInLecture(
//            @PathVariable Long studentInLectureId
//            , @RequestBody PenaltyReqDto reqDto
//    ) {
//        // 1. '벌점'을 의미하는 요청값( json 형식) 을 받아서 StudentInLecture 객체를 update한다.
//        Penalty penalty = reqDto.getPenalty();
//        long updatedStuInLecId = studentInLectureService.updatePenalty(studentInLectureId, penalty);
//
//        return new RspTemplate<>(HttpStatus.OK
//                , updatedStuInLecId + "번 수강신청의 벌점이 수정되었습니다.");
//    }

    //수정 후 벌점 api
    @PatchMapping("/student-in-lectures/{studentInLectureId}")
    public RspTemplate<Void> handleUpdateStudentInLecture(
            @PathVariable Long studentInLectureId,
            @RequestBody PenaltyReqDto reqDto
    ) {
        // 1. '벌점'을 의미하는 요청값(json 형식)을 받아서 StudentInLecture 객체를 update한다.
        Penalty penalty = reqDto.getPenalty();

        // 2. StudentInLecture 객체를 찾아온다.
        StudentInLecture studentInLecture = studentInLectureService.getById(studentInLectureId);

        // 3. 학생의 총 벌점을 가져온다.
        int totalPenalty = studentInLecture.getStudent().getTotalPenalty();

        // 4. 이전 벌점 정보를 가져온다.
        Penalty previousPenalty = Penalty.NONE;
        if (studentInLecture.getPenalty() != null) {
            previousPenalty = Penalty.values()[studentInLecture.getPenalty()];
        }

        // 5. 학생의 벌점을 업데이트한다.
        totalPenalty -= previousPenalty.getValue(); // 이전 벌점 감산
        totalPenalty += penalty.getValue(); // 새로운 벌점 누적
        studentInLecture.getStudent().setTotalPenalty(totalPenalty);

        // 6. StudentInLecture 객체의 벌점을 변경한다.
        studentInLecture.setPenalty(penalty);

        // 7. StudentInLecture를 저장하고 업데이트된 정보를 반환한다.
        studentInLectureService.updatePenalty(studentInLectureId, penalty);

        return new RspTemplate<>(HttpStatus.OK,
                studentInLectureId + "번 수강신청의 벌점이 수정되었습니다. 현재 총 벌점: " + totalPenalty + "점");
    }


    /**
     *  **과제**
     *
     * 3. 요구사항 추가. 벌점부여 API 수정.
     *
     * 학생 자체의 벌점을 기록해야 함.
     * DB상의 특정 컬럼이 해당 학생의 총 벌점을 나타내야 한다는 것! (예시: Student 객체에 Integer totalPenalty 필드)
     *
     * @PatchMapping("/student-in-lectures/{studentInLectureId}")
     * 로 요청이 들어올 때,
     *
     * student 객체의 벌점 점수 (totalPenalty)를 누적해야 한다.
     *
     * 같은 요청을 여러 번 보내면, 두 번째 요청부터는 벌점이 덮어쓰기 형식으로 진행.
     *
     * 하나의 studentInLecture 를 대상으로
     * 결석으로 벌점을 수정하는 요청을 N번 보내면
     * 벌점이 10 + 10 + 10 = 30이 되는 것이 아니라
     *
     * 10으로 고정되어있어야 함.
     *
     * 10 2 0
     * 0
     *
     * 학생1로 강의1, 2에 모두 수강신청을 해서
     * id 1, 2를 가진 studentInLecture 데이터가 생성되었다고 가정.
     *
     * @PatchMapping("/student-in-lectures/1")에
     * '결석' 벌점부과 요청을 보내면
     * 학생1의 totalPenalty는 10점이 되어야 한다. (0 + 10 = 10)
     *
     * 이후 @PatchMapping("/student-in-lectures/2")에
     * '지각' 벌점부과 요청을 보내면
     * 학생1의 totalPenalty는 12점이 되어야 한다. (10 + 2 = 12)
     *
     * 그런데 처음 벌점부과 요청은 관리자의 실수였다고 한다!
     * 다시 @PatchMapping("/student-in-lectures/1") 로
     * '없음' 벌점부과 요청을 보내면
     * '결석' 처리가 '없음' 으로 변경되어서
     * 학생1의 totalPenalty는 2점이 되어야 한다. (12 - 10 = 2)
     */

    /**
     *  5.
     *  Fetch Join + 페이징
     *  특정 강좌를 듣고 있는 학생의 목록 출력해야 함
     *   Lecture는 ByID()로 따로 조회하고
     *   StudentInLecture Fetch Student Where Lecture.id = lectureId
     *
     *   Fetch Join 왜하냐?
     *   데이터를 다룰 때는 보수적으로 가는 게 좋음
     *   전체 Eager - 필요할 때 Lazy는 그런 방법이 없을 뿐더러 예측이 힘듬
     *   전체 Lazy - 필요할 때 Eager(Fetch Join)이 관리가 쉬움
     */





}
