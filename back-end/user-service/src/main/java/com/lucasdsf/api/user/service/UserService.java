package com.lucasdsf.api.user.service;

import java.util.List;

import com.lucasdsf.api.user.domains.model.UserDto;

public interface UserService {
	UserDto createUser(UserDto user);
	UserDto getInfoUser(Long id);
	UserDto updateUserEmail(Long id, UserDto userDto);
	List<UserDto> listAll();
}
