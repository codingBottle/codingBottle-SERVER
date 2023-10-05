package com.example.task2.domain.lecture;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LectureService {

    private final LectureRepository lectureRepository;

    public Lecture getById(Long lectureId) {

        Optional<Lecture> optionalLecture = lectureRepository.findById(lectureId);

//      강의가 없으면 에러 -> 예외 처리
        if (optionalLecture.isEmpty()) {
            throw new IllegalArgumentException("해당 강의가 존재하지 않습니다.");
        }
        return optionalLecture.get();

//      java 8 문법
//      return lectureRepository.findById(lectureId).orElseThrow(() -> new IllegalArgumentException("해당 강의가 존재하지 않습니다."));
    }
}