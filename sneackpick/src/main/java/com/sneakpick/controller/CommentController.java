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

import com.sneakpick.entities.Comment;
import com.sneakpick.entities.Product;
import com.sneakpick.entities.User;
import com.sneakpick.services.CommentService;
import com.sneakpick.services.ProductService;
import com.sneakpick.services.UserService;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;

	@GetMapping()
	public ResponseEntity<List<Comment>> getAllComments() {
		List<Comment> comments = commentService.findAll();
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<List<Comment>> getCommentByProduct(@PathVariable("id") Long id) {
		List<Comment> comments = commentService.findByProductId(id);
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Comment> addComment(@RequestParam(value = "product", required = false) String productId,
			@RequestParam(value = "comment", required = false) String commentParam, Principal p) {
		User user = userService.findByEmail(p.getName());
		Product product = productService.findProductById(Long.parseLong(productId));
		Comment newComment = new Comment();
		newComment.setProduct(null);
		newComment.setCommentText(commentParam);
		newComment.setUser(user);
		newComment.setProduct(product);
		commentService.save(newComment);
		return new ResponseEntity<>(newComment, HttpStatus.CREATED);
	}

	@PutMapping()
	public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) {
		Comment updateComment = commentService.save(comment);
		return new ResponseEntity<>(updateComment, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable("id") Long id) {
		commentService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
