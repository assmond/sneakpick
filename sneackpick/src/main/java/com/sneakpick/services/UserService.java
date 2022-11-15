package com.sneakpick.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import com.sneakpick.dto.LocalUser;
import com.sneakpick.dto.SignUpRequest;
import com.sneakpick.entities.User;
import com.sneakpick.exception.UserAlreadyExistAuthenticationException;

public interface UserService {

	User findById(Long id);

	void deleteById(Long id);

//	User findByUsername(String username);

	User findByEmail(String email);

	User save(User user);

	List<User> findAll();

//	User createUser(String username, String email, String password, String role);

	public User registerNewUser(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

	User findUserByEmail(String email);

	Optional<User> findUserById(Long id);

	LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken,
			OidcUserInfo userInfo);
}
