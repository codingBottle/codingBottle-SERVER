package com.example.serversessionjparestful.domain;

import com.example.serversessionjparestful.api.dto.UpdatePostRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_num")
    private Long id;

    //제목
    @Column(length = 500)
    private String title;

    //텍스트
    @Column(name = "post_content")
    @Lob
    private String content;


    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void UpdateValidate(UpdatePostRequest updatePostRequest) {
        if (ObjectUtils.isEmpty(updatePostRequest))
            throw new IllegalArgumentException("요청 파라미터가 NULL입니다.");
        if (updatePostRequest.getTitle() != null) {
            this.title = updatePostRequest.getTitle();
        }
        if (updatePostRequest.getContent() != null) {
            this.content = updatePostRequest.getContent();
        }

    }
}
