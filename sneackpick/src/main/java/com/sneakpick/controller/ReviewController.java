package com.sneakpick.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sneakpick.entities.Product;
import com.sneakpick.entities.Review;
import com.sneakpick.entities.User;
import com.sneakpick.services.ProductService;
import com.sneakpick.services.ReviewService;
import com.sneakpick.services.UserService;

@RestController
@RequestMapping("api/v1/reviews")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;

	@GetMapping()
	public ResponseEntity<List<Review>> getAllReviews() {
		List<Review> reviews = reviewService.findAll();
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Review> getReviewByProduct(@PathVariable("id") Long id) {
		List<Review> reviews = reviewService.findByProductId(id);
		Product p = productService.findProductById(id);
		int total = 0;
		for (Review review : reviews) {
			total += review.getStars();
		}
		Review r = new Review();
		r.setProduct(p);
		if (reviews.size() > 0) {
			r.setStars(total / reviews.size());
		} else {
			r.setStars(total);
		}

		return new ResponseEntity<>(r, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Review> addReview(@RequestParam(value = "product", required = false) String productId,
			@RequestParam(value = "stars", required = false) String stars, Principal p) {
		User user = userService.findByEmail(p.getName());
		Product product = productService.findProductById(Long.parseLong(productId));
		Review newReview = new Review();
		newReview.setProduct(null);
		newReview.setStars(Integer.parseInt(stars));
		newReview.setUser(user);
		newReview.setProduct(product);
		reviewService.save(newReview);
		return new ResponseEntity<>(newReview, HttpStatus.CREATED);
	}

	@PutMapping()
	public ResponseEntity<Review> updateReview(@RequestBody Review review) {
		Review updateReview = reviewService.save(review);
		return new ResponseEntity<>(updateReview, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteReview(@PathVariable("id") Long id) {
		reviewService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
