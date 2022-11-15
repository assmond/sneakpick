package com.sneakpick.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sneakpick.dto.ApiResponse;
import com.sneakpick.dto.JwtAuthenticationResponse;
import com.sneakpick.dto.LocalUser;
import com.sneakpick.dto.LoginRequest;
import com.sneakpick.dto.SignUpRequest;
import com.sneakpick.exception.UserAlreadyExistAuthenticationException;
import com.sneakpick.mail.EmailDetails;
import com.sneakpick.security.jwt.TokenProvider;
import com.sneakpick.services.EmailService;
import com.sneakpick.services.UserService;
import com.sneakpick.util.GeneralUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	private EmailService emailService;

	@Autowired
	UserService userService;

	@Autowired
	TokenProvider tokenProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.createToken(authentication);
		LocalUser localUser = (LocalUser) authentication.getPrincipal();
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, GeneralUtils.buildUserInfo(localUser)));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		try {
			userService.registerNewUser(signUpRequest);
		} catch (UserAlreadyExistAuthenticationException e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"),
					HttpStatus.BAD_REQUEST);
		}
		EmailDetails details = new EmailDetails();
		details.setMsgBody("Hi " + signUpRequest.getFirstName() + "  " + signUpRequest.getLastName()
				+ ",\n Your account has been created successfully. \n  your Login info: \n" + " Email:"
				+ signUpRequest.getEmail() + "\n" + "Password: " + signUpRequest.getPassword() + "\n" + "Best regards");
		details.setRecipient(signUpRequest.getEmail());
		details.setSubject("Sneak Pick registration");
		String status = emailService.sendSimpleMail(details);
		System.out.println("Your account has been created successfully");
		return ResponseEntity.ok().body(new ApiResponse(true, "User registered successfully"));
	}
}