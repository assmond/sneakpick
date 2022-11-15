package com.sneakpick.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.sneakpick.dto.LocalUser;
import com.sneakpick.dto.SignUpRequest;
import com.sneakpick.dto.SocialProvider;
import com.sneakpick.entities.Role;
import com.sneakpick.entities.User;
import com.sneakpick.exception.OAuth2AuthenticationProcessingException;
import com.sneakpick.exception.UserAlreadyExistAuthenticationException;
import com.sneakpick.exception.UserNotFoundException;
import com.sneakpick.repositories.RoleRepository;
import com.sneakpick.repositories.UserRepository;
import com.sneakpick.security.oauth2.user.OAuth2UserInfo;
import com.sneakpick.security.oauth2.user.OAuth2UserInfoFactory;
import com.sneakpick.services.UserService;
import com.sneakpick.util.GeneralUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User findById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
	}

//	@Override
//	public User findByUsername(String username) {
//		return userRepository.findByUsername(username);
//	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

//	@Override
//	@Transactional
//	public User createUser(String username, String password, String email, String role) {
//		User user = findByUsername(username);
//		if (user != null) {
//			return user;
//		} else {
//			user = new User();
//			user.setUsername(username);
//			user.setPassword(encoder.encode(password));
//			user.setEmail(email);
//			Role roleDb = roleRepository.findByName(role);
//			user.setRole(roleDb);
//			return userRepository.save(user);
//		}
//	}

	@Override
	@Transactional(value = "transactionManager")
	public User registerNewUser(final SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException {
		if (signUpRequest.getUserID() != null && userRepository.existsById(signUpRequest.getUserID())) {
			throw new UserAlreadyExistAuthenticationException(
					"User with User id " + signUpRequest.getUserID() + " already exist");
		} else if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new UserAlreadyExistAuthenticationException(
					"User with email id " + signUpRequest.getEmail() + " already exist");
		}
		User user = buildUser(signUpRequest);
		Date now = Calendar.getInstance().getTime();
		user.setCreatedDate(now);
		user.setModifiedDate(now);
		user = userRepository.save(user);
		userRepository.flush();
		return user;
	}

	private User buildUser(final SignUpRequest formDTO) {
		User user = new User();
		user.setFirstName(formDTO.getFirstName());
		user.setLastName(formDTO.getLastName());
		user.setEmail(formDTO.getEmail());
		user.setPassword(encoder.encode(formDTO.getPassword()));
		final HashSet<Role> roles = new HashSet<Role>();
		roles.add(roleRepository.findByName(Role.ROLE_USER));
		user.setRoles(roles);
		if (formDTO.getSocialProvider() != null) {
			user.setProvider(formDTO.getSocialProvider().getProviderType());
		} else {
			user.setProvider("Local");
		}

		user.setEnabled(true);
		if (formDTO.getProviderUserId() != null) {
			user.setProviderUserId(formDTO.getProviderUserId());
		} else {
			user.setProviderUserId("Local");
		}

		return user;
	}

	@Override
	public User findUserByEmail(final String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken,
			OidcUserInfo userInfo) {
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
		if (StringUtils.isEmpty(oAuth2UserInfo.getName())) {
			throw new OAuth2AuthenticationProcessingException("Name not found from OAuth2 provider");
		} else if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
		}
		SignUpRequest userDetails = toUserRegistrationObject(registrationId, oAuth2UserInfo);
		User user = findUserByEmail(oAuth2UserInfo.getEmail());
		if (user != null) {
			if (!user.getProvider().equals(registrationId)
					&& !user.getProvider().equals(SocialProvider.LOCAL.getProviderType())) {
				throw new OAuth2AuthenticationProcessingException(
						"Looks like you're signed up with " + user.getProvider() + " account. Please use your "
								+ user.getProvider() + " account to login.");
			}
			user = updateExistingUser(user, oAuth2UserInfo);
		} else {
			user = registerNewUser(userDetails);
		}

		return LocalUser.create(user, attributes, idToken, userInfo);
	}

	private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
		existingUser.setFirstName(oAuth2UserInfo.getName());
		existingUser.setLastName(oAuth2UserInfo.getName());
		return userRepository.save(existingUser);
	}

	private SignUpRequest toUserRegistrationObject(String registrationId, OAuth2UserInfo oAuth2UserInfo) {
		return SignUpRequest.getBuilder().addProviderUserID(oAuth2UserInfo.getId())
				.addFirstName(oAuth2UserInfo.getName()).addLastName(oAuth2UserInfo.getName())
				.addEmail(oAuth2UserInfo.getEmail()).addSocialProvider(GeneralUtils.toSocialProvider(registrationId))
				.addPassword("changeit").build();
	}

	@Override
	public Optional<User> findUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

}
