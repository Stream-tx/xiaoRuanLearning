package com.example.communityservice.repository;

import com.example.communityservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllBySolutionId(Long solutionId);
}
