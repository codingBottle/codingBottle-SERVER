package com.example.serversessionjparestful.api.dto;

import com.example.serversessionjparestful.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddPostResponse {
    private Long PostId;
    private String title;
    private String content;

    public static AddPostResponse createInstance(Post post) {
        return new AddPostResponse(post.getId(), post.getTitle(), post.getContent());
    }


}
