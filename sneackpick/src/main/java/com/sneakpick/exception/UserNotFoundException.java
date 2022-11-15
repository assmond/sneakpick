package com.sneakpick.exception;

/**
 * 
 * User not found exception
 *
 */
public class UserNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1884420377672517776L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
