package com.veeam.interview.novozhilov.blogs.service;

import com.veeam.interview.novozhilov.blogs.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService {
    @Autowired
    private PostsRepo postsRepo;
    @Autowired
    private AuthorsRepo authorsRepo;

    @Autowired
    private CommentsRepo commentsRepo;



    public Comment createNewComment(CommentSmall inCommentSmall, Long postId) {
        Author author = authorsRepo.findById(inCommentSmall.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException(inCommentSmall.getAuthorId()));
        Post post = postsRepo.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        Comment newComment = new Comment(
                null, inCommentSmall.getContent(), post, author, new Date(), false);

        return commentsRepo.save(newComment);
    }

    public Page<Comment> getAllComments(Pageable pageable){
        return commentsRepo.findAll(pageable);
    }

    public Comment updateComment(CommentSmall inCommentSmall, Long id) {
        Comment comment = commentsRepo.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
        comment.setContent(inCommentSmall.getContent());
        comment.setUpdatedAt(new Date());
        //автора комментария и пост не изменяем, т.к. это не имеет смысла
        return commentsRepo.save(comment);
    }

    public Page<Comment> findCommentsByAuthorId(CommentSmall inCommentSmall,
                                                Pageable pageable) {
        return commentsRepo.findByAuthorId(inCommentSmall.getAuthorId(), pageable);
    }

    public ResponseEntity<?> deleteComment(Long id) {
        Comment comment = commentsRepo.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
        comment.setDeleted(true);
        commentsRepo.save(comment);
        return ResponseEntity.ok().build();
    }

    public Page<Comment> getAllCommentsUnderPost(Pageable pageable, Long postId) {
        return commentsRepo.findByPostId(postId, pageable);
    }
}
