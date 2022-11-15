package com.sneakpick.dto;

import java.util.List;

import lombok.Value;

/**
 * 
 * @author User object to use as response for connected user
 *
 */
@Value
public class UserInfo {
	private String id, firstName, lastName, email;
	private boolean enabled;
	private List<String> roles;
}