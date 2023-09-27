package com.example.serversessionjparestful.domain.repository;

import com.example.serversessionjparestful.api.dto.UpdatePostRequest;
import com.example.serversessionjparestful.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImp implements PostRepository{

    private final EntityManager em;
    @Override
    //게시물 생성
    public Post save(Post post) {
        em.persist(post);
        return post;
    }

    @Override
    public void update(UpdatePostRequest updateParam, Post post) {
        post.UpdateValidate(updateParam);
    }

    @Override
    public void delete(Post post) {
        em.remove(post);
    }

    @Override
    //게시물 불러오기
    public Optional<Post> findById(Long postId) {
        Post post = em.find(Post.class, postId);
        return Optional.ofNullable(post);

    }

    @Override
    public List<Post> findAll() {
        return em.createQuery("select p from Post p ",Post.class)
                .getResultList();
    }
}
