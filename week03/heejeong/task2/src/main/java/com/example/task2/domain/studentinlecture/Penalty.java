package com.example.task2.domain.studentinlecture;

import lombok.Getter;

@Getter     // 필드를 만들기 때문에 사용 (여기서는 value)
public enum Penalty {

//  없음(-0점), 지각(-2점), 결석(-10점)
    NONE(0), LATE(2), ABSENT(10);

    private final int value;

    Penalty(int value) {
        this.value = value;
    }
}
