package com.sneakpick.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sneakpick.dto.LocalUser;
import com.sneakpick.entities.User;
import com.sneakpick.exception.ResourceNotFoundException;
import com.sneakpick.repositories.UserRepository;
import com.sneakpick.services.UserService;
import com.sneakpick.util.GeneralUtils;

@Service
public class LocalUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepository.findByUsername(username);
//		if (user == null) {
//			throw new UsernameNotFoundException("Username not found");
//		}
//		return user;
//	}
//
//	public void authenticateUser(String username) {
//		UserDetails userDetails = loadUserByUsername(username);
//		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
//				userDetails.getAuthorities());
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//	}

	@Override
	@Transactional
	public LocalUser loadUserByUsername(final String email) throws UsernameNotFoundException {
		User user = userService.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User " + email + " was not found in the database");
		}
		return createLocalUser(user);
	}

	@Transactional
	public LocalUser loadUserById(Long id) {
		User user = userService.findUserById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return createLocalUser(user);
	}

	/**
	 * @param user
	 * @return
	 */
	private LocalUser createLocalUser(User user) {
		return new LocalUser(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true,
				GeneralUtils.buildSimpleGrantedAuthorities(user.getRoles()), user);
	}
}
