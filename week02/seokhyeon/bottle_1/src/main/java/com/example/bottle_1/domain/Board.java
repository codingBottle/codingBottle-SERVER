package com.example.bottle_1.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 만듬
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키생성 db에 위임
    @Column(name = "board_id")
    private Long boardId;

    private String boardName;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id", updatable = false)
    private Member member;

    @Builder//객체 생성 단순화 수정은 못함
    public Board(String boardName, String title, String content, Member member){
        this.boardName = boardName;
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void update(String boardName, String title, String content) {
        this.boardName = boardName;
        this.title = title;
        this.content = content;
    }

}
