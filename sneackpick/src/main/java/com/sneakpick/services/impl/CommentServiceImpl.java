package com.sneakpick.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sneakpick.entities.Comment;
import com.sneakpick.exception.NotFoundException;
import com.sneakpick.repositories.CommentRepository;
import com.sneakpick.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository repository;

	@Override
	public Comment findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<Comment> findByUserId(Long id) {
		return repository.findByUserId(id);
	}

	@Override
	public List<Comment> findByProductId(Long id) {
		return repository.findByProductId(id);
	}

	@Override
	public Comment save(Comment review) {
		return repository.save(review);
	}

	@Override
	public List<Comment> findAll() {
		return repository.findAll();
	}

}
