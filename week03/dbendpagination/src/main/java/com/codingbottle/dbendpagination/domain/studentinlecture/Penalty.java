package com.codingbottle.dbendpagination.domain.studentinlecture;


import lombok.Getter;

@Getter
public enum Penalty {
    // 없음, 지각, 결석
    NONE(0), LATE(2), ABSENT(10);

    private final int value;

    Penalty(int value) {
        this.value = value;
    }
}
