package com.sneakpick.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sneakpick.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findByUserEmail(String email);

	List<Review> findByProductId(Long id);
}
