package com.example.serversessionjparestful.api;

import com.example.serversessionjparestful.api.dto.*;
import com.example.serversessionjparestful.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import com.example.serversessionjparestful.domain.Post;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/api/post")
    public Object createPost(@RequestBody AddPostRequest addPostRequest) {
        return new ResponseEntity<>(AddPostResponse.createInstance(postService.savePost(addPostRequest)), HttpStatus.CREATED);
    }

    @GetMapping("/api/post/{postId}")
    public Object findById(@PathVariable Long postId) {
        return PostResponse.createInstance(postService.findById(postId));    // Lazy n+1문제 때문에 findByIdLeftJoin 호출
    }

    @GetMapping("/api/post")//조회는 시간순으로 페이징 조회
    public Object findAll() {
        List<Post> findPosts = postService.findAll();
        List<PostListResponse> collect = findPosts.stream().map((m) -> PostListResponse.createInstance(m)).collect(Collectors.toList());
        return Result.createInstance(collect);
    }

    @PatchMapping("/api/post/{id}")
    public Object postUpdate(@PathVariable(name = "id") Long postid,@RequestBody UpdatePostRequest updatePostRequest) {
        postService.updatePost(updatePostRequest,postid);
        return PostResponse.createInstance(postService.findById(postid));
    }

    @DeleteMapping("/api/post/{id}")
    public Object postDelete(@PathVariable(name = "id") Long postId) {
        postService.delete(postId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
