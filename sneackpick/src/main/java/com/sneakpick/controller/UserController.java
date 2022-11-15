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
import org.springframework.web.bind.annotation.RestController;

import com.sneakpick.dto.UserInfo;
import com.sneakpick.entities.User;
import com.sneakpick.mail.EmailDetails;
import com.sneakpick.services.EmailService;
import com.sneakpick.services.UserService;
import com.sneakpick.util.GeneralUtils;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserService userService;

	@PostMapping("/sendMail")
	public String sendMail(@RequestBody EmailDetails details) {
		String status = emailService.sendSimpleMail(details);

		return status;
	}

	@GetMapping("/load-user")
	public UserInfo obtenerUsuarioActual(Principal principal) {
		User user = this.userService.findByEmail(principal.getName());
		return GeneralUtils.buildUserInfo(user);
	}

	@GetMapping()
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
		User employee = userService.findById(id);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User newUser = userService.save(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	@PutMapping()
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		User updateUser = userService.save(user);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		userService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

//	@GetMapping("/{email}")
//	public User getUser(@PathVariable("email") String email) {
//		return userService.findByEmail(email);
//	}
//
//	@DeleteMapping("/{id}")
//	public void eliminarUsuario(@PathVariable("id") Long userId) {
//		userService.deleteById(userId);
//	}
}
