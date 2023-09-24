package com.example.serversessionjparestful.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private String title;

    private String contents;

    private String createDate;

    @Builder
    private Board(String title, String contents, String createDate) {
        this.title = title;
        this.contents = contents;
        this.createDate = createDate;
    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

}
