package com.example.task1.api.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/*
    Response Body 기본 설정
 */
@Getter
public class RspTemple<T> {

    int status;
    String message;
    T data;

    public RspTemple(HttpStatus status, String message, T data) {
        this.status = status.value();
        this.message = message;
        this.data = data;
    }

    public RspTemple(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }
}
