package com.example.communityservice.service.impl;

import com.example.communityservice.entity.Comment;
import com.example.communityservice.repository.CommentRepository;
import com.example.communityservice.sensitiveWordUtil.SensitiveWordUtil;
import com.example.communityservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private SensitiveWordUtil sensitiveWordUtil;
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
    public boolean addComment(Comment comment) {
        sensitiveWordUtil.init();
        System.out.println("11");
        System.out.println(comment.getContent());
        if(!sensitiveWordUtil.contains(comment.getContent())){
            comment.setCommentTime(LocalDate.now());
            commentRepository.save(comment);
            return true;
        }
        return false;
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
