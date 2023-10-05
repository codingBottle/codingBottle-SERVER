package com.example.task1.domain.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

/*
 * 등록자 및 수정자 설정
 */
@Getter
@MappedSuperclass   // 해당 클래스를 컬럼으로 인식
@EntityListeners(value = {AuditingEntityListener.class})    // JPA auditing 항목 설정
public class BasePerson extends BaseTime {

    @CreatedBy
    @Column(updatable = false)      // 컬럼 수정 이후에도 기존 데이터 유지
    private String firstPerson;

    @LastModifiedBy
    private String lastPerson;
}
