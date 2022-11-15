package com.sneakpick.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.sneakpick.dto.LocalUser;
import com.sneakpick.dto.SocialProvider;
import com.sneakpick.dto.UserInfo;
import com.sneakpick.entities.Role;
import com.sneakpick.entities.User;

/**
 * 
 * @author valentine
 *
 */
public class GeneralUtils {

	public static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<Role> roles) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	public static SocialProvider toSocialProvider(String providerId) {
		for (SocialProvider socialProvider : SocialProvider.values()) {
			if (socialProvider.getProviderType().equals(providerId)) {
				return socialProvider;
			}
		}
		return SocialProvider.LOCAL;
	}

	public static UserInfo buildUserInfo(LocalUser localUser) {
		List<String> roles = localUser.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		User user = localUser.getUser();
		return new UserInfo(user.getId().toString(), user.getFirstName(), user.getLastName(), user.getEmail(),
				user.isEnabled(), roles);
	}

	public static UserInfo buildUserInfo(User userDb) {
		List<String> roles = userDb.getRoles().stream().map(item -> item.getName()).collect(Collectors.toList());
		User user = userDb;
		return new UserInfo(user.getId().toString(), user.getFirstName(), user.getLastName(), user.getEmail(),
				user.isEnabled(), roles);
	}
}
