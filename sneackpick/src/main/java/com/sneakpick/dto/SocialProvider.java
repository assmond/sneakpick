package com.sneakpick.dto;

/**
 *
 * Social provider to add here
 */
public enum SocialProvider {

	GOOGLE("google"), LOCAL("local");

	private String providerType;

	public String getProviderType() {
		return providerType;
	}

	SocialProvider(final String providerType) {
		this.providerType = providerType;
	}

}
