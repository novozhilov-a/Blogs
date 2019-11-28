package com.veeam.interview.novozhilov.blogs.controllers;

import com.veeam.interview.novozhilov.blogs.model.Comment;
import com.veeam.interview.novozhilov.blogs.model.CommentSmall;
import com.veeam.interview.novozhilov.blogs.service.CommentService;
import com.veeam.interview.novozhilov.blogs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    Comment createNewComment(
            @RequestBody CommentSmall inCommentSmall,
            @PathVariable Long postId){
        return commentService.createNewComment(inCommentSmall, postId);
    }

    @GetMapping("/posts/{postId}/comments")
    Page<Comment> getAllCommentsUnderPost(
            Pageable pageable,
            @PathVariable Long postId){
        return commentService.getAllCommentsUnderPost(pageable, postId);
    }

    @PostMapping("/comments/findByAuthorId")
    Page<Comment> findCommentsByAuthorId(@RequestBody CommentSmall inCommentSmall,
                                     Pageable pageable) {
        return commentService.findCommentsByAuthorId(inCommentSmall, pageable);
    }

    @GetMapping("/comments")
    Page<Comment> getAllComments(Pageable pageable){
        return commentService.getAllComments(pageable);
    }

    @PutMapping("/comments/{id}")
    Comment updateComment(@RequestBody CommentSmall inCommentSmall, @PathVariable Long id){
        return commentService.updateComment(inCommentSmall, id);
    }

    @DeleteMapping("/comments/{id}")
    ResponseEntity<?> deleteComment(@PathVariable Long id){
        return commentService.deleteComment(id);
    }




}
