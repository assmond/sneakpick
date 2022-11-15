package com.sneakpick.services;

import java.util.List;

import com.sneakpick.entities.Review;

public interface ReviewService {

	Review findById(Long id);

	void deleteById(Long id);

	List<Review> findByUserEmail(String email);

	List<Review> findByProductId(Long id);

	Review save(Review review);

	List<Review> findAll();
}
