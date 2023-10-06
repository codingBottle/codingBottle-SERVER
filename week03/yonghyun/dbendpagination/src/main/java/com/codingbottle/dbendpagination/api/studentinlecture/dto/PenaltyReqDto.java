package com.codingbottle.dbendpagination.api.studentinlecture.dto;

import com.codingbottle.dbendpagination.domain.studentinlecture.Penalty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PenaltyReqDto { //  역직렬화
    Penalty penalty;
}
