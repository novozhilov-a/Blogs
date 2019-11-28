package com.veeam.interview.novozhilov.blogs.controllers;

import com.veeam.interview.novozhilov.blogs.model.Post;
import com.veeam.interview.novozhilov.blogs.model.PostSmall;
import com.veeam.interview.novozhilov.blogs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/posts")
    Post createNewPost(@RequestBody PostSmall inPostSmall){
        return postService.createNewPost(inPostSmall);
    }

    @PutMapping("/posts/{postId}")
    Post updatePost(@RequestBody PostSmall inPostSmall, @PathVariable Long postId){
        return postService.updatePost(inPostSmall, postId);
    }

    @DeleteMapping("/posts/{postId}")
    ResponseEntity<?> deletePost(@PathVariable Long postId){
        return postService.deletePost(postId);
    }

    @PostMapping("/posts/findByAuthorId")
    Page<Post> findPostByAuthorId(@RequestBody PostSmall inPostSmall,
                                  Pageable pageable){
        return postService.findPostByAuthorId(inPostSmall, pageable);
    }

    @GetMapping("/posts")
    Page<Post> getAllPosts(Pageable pageable){
        return postService.getAllPosts(pageable);
    }
}
