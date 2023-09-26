package com.example.task1.api.dto.response;

import com.example.task1.domain.Board;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardList {

    //  게시물의 컬럼을 리스트로 반환
    List<Board> boards;

    public BoardList(List<Board> boards) {
        this.boards = boards;
    }

    @Builder    // DTO를 Entity로 변경
    @Getter
    static class BoardDto {     // 조회 시 사용할 DTO
        private long boardId;
        private String title;
        private String content;
        private String lastPerson;
        private LocalDateTime lastTime;

        public static BoardDto toEntity(Board board) {
            return BoardDto.builder()
                    .boardId(board.getBoardId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .lastPerson(board.getLastPerson())
                    .lastTime(board.getLastTime())
                    .build();
        }

        // Board의 List를 받아 BoardDto의 List로 변환
        public static List<BoardDto> toEntity(List<Board> boards) {
            return boards.stream()
                    .map(BoardDto::toEntity)        // BoardDto의 toEntity 메서드 매핑
                    .collect(Collectors.toList());  // List로 반환
        }
    }
}
