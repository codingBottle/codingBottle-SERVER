package com.example.task1.domain;

import com.example.task1.domain.common.BasePerson;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * 보드 테이블 설정
 * 1. id
 * 2. 글 제목
 * 3. 글 내용
 * 4. 등록 날짜 및 등록자
 * 5. 수정 날짜 및 수정자
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BasePerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long boardId;

    String title;

    String content;

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
