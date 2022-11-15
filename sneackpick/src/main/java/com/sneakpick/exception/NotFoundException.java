package com.sneakpick.exception;

/**
 * 
 * Resource not found exception
 *
 */
public class NotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1884420377672517776L;

	public NotFoundException(String message) {
		super(message);
	}

}
