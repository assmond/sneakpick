package com.sneakpick.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sneakpick.entities.Review;
import com.sneakpick.exception.NotFoundException;
import com.sneakpick.repositories.ReviewRepository;
import com.sneakpick.services.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewRepository repository;

	@Override
	public Review findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Not  found"));
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<Review> findByUserEmail(String email) {
		return repository.findByUserEmail(email);
	}

	@Override
	public List<Review> findByProductId(Long id) {
		return repository.findByProductId(id);
	}

	@Override
	public Review save(Review review) {
		return repository.save(review);
	}

	@Override
	public List<Review> findAll() {
		return repository.findAll();
	}

}
