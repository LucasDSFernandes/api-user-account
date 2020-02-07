package com.lucasdsf.api.user.builder;

import com.lucasdsf.api.user.domains.model.UserDto;

public class UserDtoBuilder {
	private Long id;
	private String login;
	private String fullName;
	private String firtName;
	private String email;
	
	public static UserDtoBuilder userDTOBuilder() {
		return new UserDtoBuilder();
	}
	
	public UserDtoBuilder id(Long id) {
		this.id = id;
		return this;		
	}
	public UserDtoBuilder firtName(String firtName) {
		this.firtName = firtName;
		return this;		
	}
	public UserDtoBuilder fullName(String fullName) {
		this.fullName = fullName;
		return this;		
	}
	public UserDtoBuilder login(String login) {
		this.login = login;
		return this;		
	}
	public UserDtoBuilder email(String email) {
		this.email = email;
		return this;		
	}
	
	public UserDto build() {
		UserDto userDto = new UserDto();
		userDto.setUserId(id);
		userDto.setEmail(email);
		userDto.setLogin(login);
		userDto.setFullName(fullName);
		userDto.setFirstName(firtName);
		return userDto;
	} 

}
