package com.lucasdsf.api.user.builder;

import com.lucasdsf.api.user.domains.entity.ProfileUser;
import com.lucasdsf.api.user.domains.entity.User;

public class UserEntityBuilder {
	
	private Long userId;
	private String email;
	private String password;
	private ProfileUser profile;
	private String login;
	private String firstName;
	private String userFull;
	
	public static UserEntityBuilder userBuilder() {
		return new UserEntityBuilder();
	}
	
	public UserEntityBuilder userId(Long userId) {
		this.userId = userId;
		return this;
	}
	
	public UserEntityBuilder userFull(String userFull) {
		this.userFull = userFull;
		return this;		
	}
	public UserEntityBuilder firstName(String firstName) {
		this.firstName = firstName;
		return this;		
	}
	public UserEntityBuilder email(String email) {
		this.email = email;
		return this;		
	}
	public UserEntityBuilder password(String password) {
		this.password = password;
		return this;		
	}
	public UserEntityBuilder profile(ProfileUser profile) {
		this.profile = profile;
		return this;		
	}
	public UserEntityBuilder login(String login) {
		this.login = login;
		return this;		
	}
	
	public User build() {
		User user = new User();
		user.setId(userId);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setFullName(userFull);
		user.setUserLogin(login);
		user.setPassword(password);
		user.setProfile(profile);
		return user;
	} 

}
