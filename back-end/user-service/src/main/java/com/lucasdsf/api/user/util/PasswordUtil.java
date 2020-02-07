package com.lucasdsf.api.user.util;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
	
	public static String generateBCrypt(String password) {
		if (Optional.ofNullable(password).isPresent()) {
			BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
			return bCryptEncoder.encode(password.trim());
		}else{
			return password;
		}

	}

	public static boolean isPassWordValid(String password, String encodedPassword) {
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.matches(password, encodedPassword);
	}

}
