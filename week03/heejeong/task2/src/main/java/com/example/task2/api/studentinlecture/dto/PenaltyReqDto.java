package com.example.task2.api.studentinlecture.dto;

import com.example.task2.domain.studentinlecture.Penalty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PenaltyReqDto {    // 역직렬화
    Penalty penalty;
}