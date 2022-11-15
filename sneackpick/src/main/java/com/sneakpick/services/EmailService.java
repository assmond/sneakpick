package com.sneakpick.services;

import com.sneakpick.mail.EmailDetails;

public interface EmailService {
	String sendSimpleMail(EmailDetails details);
}
