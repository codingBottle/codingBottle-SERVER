package com.example.task1.domain.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
 * 등록일 및 수정일 설정
 */

@Getter
@MappedSuperclass   // 해당 클래스를 컬럼으로 인식
@EntityListeners(value = {AuditingEntityListener.class})    // JPA auditing 항목 설정
public class BaseTime {

    @CreatedDate
    @Column(updatable = false)      // 컬럼 수정 이후에도 기존 데이터 유지
    private LocalDateTime firstTime;

    @LastModifiedDate
    private LocalDateTime lastTime;
}
