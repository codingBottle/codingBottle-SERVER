package com.example.serversessionjparestful.domain.repository;

import com.example.serversessionjparestful.api.dto.UpdatePostRequest;
import com.example.serversessionjparestful.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);

    void update(UpdatePostRequest updateParam, Post post);

    void delete(Post post);

    Optional<Post> findById(Long postId);

    List<Post> findAll();
}
