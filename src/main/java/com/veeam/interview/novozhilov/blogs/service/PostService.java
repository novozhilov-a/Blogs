package com.veeam.interview.novozhilov.blogs.service;

import com.veeam.interview.novozhilov.blogs.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostService {
    @Autowired
    private PostsRepo postsRepo;
    @Autowired
    private AuthorsRepo authorsRepo;



    public Post createNewPost(PostSmall inPostSmall) {
        Author author = authorsRepo.findById(inPostSmall.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException(inPostSmall.getAuthorId()));
        Post post = new Post(null, inPostSmall.getContent(), author, new Date(), false);

        return postsRepo.save(post);
    }

    public Page<Post> getAllPosts(Pageable pageable){
        return postsRepo.findAll(pageable);
    }

    public Post updatePost(PostSmall inPostSmall, Long id) {
        Post post = postsRepo.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
        post.setContent(inPostSmall.getContent());
        post.setUpdatedAt(new Date());
        //автора поста не изменяем, т.к. это неправильно
        return postsRepo.save(post);
    }

    public Page<Post> findPostByAuthorId(PostSmall inPostSmall, Pageable pageable) {
        return postsRepo.findByAuthorId(inPostSmall.getAuthorId(), pageable);
    }

    public ResponseEntity<?> deletePost(Long id) {
        Post post = postsRepo.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
        post.setDeleted(true);
        postsRepo.save(post);
        return ResponseEntity.ok().build();
    }
}
