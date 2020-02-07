package com.lucasdsf.api.user.rest.response;

import java.util.List;

import com.lucasdsf.api.user.domains.model.UserDto;

public class UserResponse {
	private List<UserDto> users;

	public UserResponse(List<UserDto> users) {
		this.users = users;
	}
	public List<UserDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}

	
}
