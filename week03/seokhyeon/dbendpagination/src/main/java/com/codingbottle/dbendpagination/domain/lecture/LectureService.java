package com.codingbottle.dbendpagination.domain.lecture;

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
        //null체크 편하게~
        //옛날 문법
        if (optionalLecture.isEmpty()) {
            throw new IllegalArgumentException("해당 강의가 존재하지 않습니다.");
        }
        return optionalLecture.get();
    }
}
