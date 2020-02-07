package com.lucasdsf.api.user.rest.request;

import com.lucasdsf.api.user.domains.model.UserDto;

public class UserRequest {
	
	private UserDto user;

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
}
