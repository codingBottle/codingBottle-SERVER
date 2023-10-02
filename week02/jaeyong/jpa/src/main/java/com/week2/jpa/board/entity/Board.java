package com.week2.jpa.board.entity;

import com.week2.jpa.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private String title;

    @Column
    private String body;

    @Builder
    public Board(Long boardId, Member member, String title, String body) {
        this.boardId = boardId;
        this.member = member;
        this.title = title;
        this.body = body;
    }

    public BoardBuilder toBuilder() {
        return builder()
                .boardId(this.boardId)
                .member(this.member);
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
