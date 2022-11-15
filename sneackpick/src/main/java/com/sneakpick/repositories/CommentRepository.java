package com.sneakpick.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sneakpick.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByUserId(Long userId);

	List<Comment> findByProductId(Long productId);

}
