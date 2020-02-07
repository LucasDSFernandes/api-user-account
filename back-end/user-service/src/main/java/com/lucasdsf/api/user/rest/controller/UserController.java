package com.lucasdsf.api.user.rest.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucasdsf.api.user.client.AuthService;
import com.lucasdsf.api.user.domains.model.UserDto;
import com.lucasdsf.api.user.rest.resouces.UserReources;
import com.lucasdsf.api.user.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController implements UserReources{

	@Autowired
	private UserService userService;
	
	@Inject
	private AuthService authService;
	
	@Override
	@PostMapping(value = "/")
	public ResponseEntity<UserDto> createUser(UserDto user) {
		return ResponseEntity.ok(userService.createUser(user));
	}
	
	@Override
	@PreAuthorize("hasAnyRole('ROLE_MASTER','ROLE_ADMIN')")
	@GetMapping(value = "/list")
	public ResponseEntity<List<UserDto>> listAll(@RequestHeader("Authorization") String authorization) {
		authService.getPrincipal(authorization);
		return ResponseEntity.ok(userService.listAll());
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_MASTER','ROLE_ADMIN','ROLE_USER')")
	@GetMapping(value = "/info/{id}")
	public ResponseEntity<UserDto> getInfoUser(@RequestHeader("Authorization") String authorization, @PathVariable("id")  Long id) {
		authService.getPrincipal(authorization);
		return ResponseEntity.ok(userService.getInfoUser(id));
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_MASTER','ROLE_ADMIN', 'ROLE_USER')")
	@PutMapping(value = "/edit/{id}")
	public ResponseEntity<UserDto> updateEmail(@RequestHeader("Authorization") String authorization, @PathVariable("id")Long id, @RequestBody  UserDto  userDto){
		authService.getPrincipal(authorization);
		return ResponseEntity.ok(userService.updateUserEmail(id, userDto));
	}
}
