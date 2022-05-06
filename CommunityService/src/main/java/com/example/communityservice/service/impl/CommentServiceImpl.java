package com.example.communityservice.service.impl;

import com.example.communityservice.entity.Comment;
import com.example.communityservice.repository.CommentRepository;
import com.example.communityservice.service.CommentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Override
    public List<Comment> listComments(Long solutionId) {
        return commentRepository.findAllBySolutionId(solutionId);
    }

    @Override
    public Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @Override
    public void likesIncrement(Long commentId) {
        Comment comment = findCommentById(commentId);
        long newLikes = comment.getLikes() + 1;
        comment.setLikes(newLikes);
        commentRepository.save(comment);
    }

    @Override
    public void addComment(Comment comment) {
        comment.setCommentTime(LocalDate.now());
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
