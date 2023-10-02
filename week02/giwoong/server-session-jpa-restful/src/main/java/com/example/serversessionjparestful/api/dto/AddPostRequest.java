package com.example.serversessionjparestful.api.dto;

import com.example.serversessionjparestful.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddPostRequest {

    private String title;
    private String content;

    public AddPostRequest(String title, String content,String categoryName) {
        this.title = title;
        this.content = content;
    }

    public Post toEntity() {
        return Post.builder()
                .title(this.title).content(this.content)
                .build();
    }

}
