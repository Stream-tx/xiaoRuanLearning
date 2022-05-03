package com.example.backend.solutionComment.repository;

import com.example.backend.solutionComment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllBySolutionId(Long solutionId);
}
