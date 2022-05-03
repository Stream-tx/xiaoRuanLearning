package com.example.backend.solutionComment.service;

import com.example.backend.solutionComment.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listComments(Long solutionId);

    Comment findCommentById(Long commentId);

    void likesIncrement(Long commentId);

    void addComment(Comment comment);

    void deleteComment(Long commentId);
}
