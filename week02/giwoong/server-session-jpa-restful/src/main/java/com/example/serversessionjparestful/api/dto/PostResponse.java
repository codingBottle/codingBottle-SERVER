package com.example.serversessionjparestful.api.dto;

import com.example.serversessionjparestful.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class PostResponse {
    private Long PostId;
    private String title;
    private String content;

    public static PostResponse createInstance(Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getContent()
        );
    }



}
