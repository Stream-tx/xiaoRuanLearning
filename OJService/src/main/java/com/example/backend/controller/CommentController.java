package com.example.backend.controller;

import com.example.backend.common.Result;
import com.example.backend.entity.Comment;
import com.example.backend.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("listComments")
    public Result listSamples(@RequestParam Long solutionId){
        List<Comment> comments = commentService.listComments(solutionId);
        return Result.success(comments);
    }

    @PostMapping("likesIncrement")
    public Result likesIncrement(@RequestParam Long commentId) {
        commentService.likesIncrement(commentId);
        return Result.success(null);
    }

    @PostMapping("addComment")
    public Result addSolution(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return Result.success(null);
    }

    @PostMapping("deleteComment")
    public Result deleteComment(@RequestParam Long commentId){
        commentService.deleteComment(commentId);
        return Result.success(null);
    }
}
