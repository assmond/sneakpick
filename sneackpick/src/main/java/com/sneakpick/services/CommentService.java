package com.sneakpick.services;

import java.util.List;

import com.sneakpick.entities.Comment;

public interface CommentService {
	Comment findById(Long id);

	void deleteById(Long id);

	List<Comment> findByUserId(Long userId);

	List<Comment> findByProductId(Long productId);

	Comment save(Comment comment);

	List<Comment> findAll();
}
