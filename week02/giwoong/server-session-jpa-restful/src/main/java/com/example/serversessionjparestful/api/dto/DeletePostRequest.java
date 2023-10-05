package com.example.serversessionjparestful.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class DeletePostRequest {

    //@NotBlank는 String 타입에서 사용
    private Long postId;

}
