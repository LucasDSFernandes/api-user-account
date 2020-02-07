package com.lucasdsf.api.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lucasdsf.api.auth.domain.entity.User;
import com.lucasdsf.api.auth.domain.model.UserDetailDto;
import com.lucasdsf.api.auth.domain.repository.UserRepository;
import com.lucasdsf.api.auth.enums.ProfileEnums;

@Service(value = "userService")
@Transactional(rollbackOn=Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		return this.create(userRepository.findFirstByUserLogin(username)
				.orElseThrow(()->new UsernameNotFoundException("Usuario e senha inválido")));
	}
	
	public UserDetailDto create(User user) {
		return new UserDetailDto(user.getUserLogin(), user.getPassword(), mapToGrantedAuthorities(user.getProfile().getDescriptionProfile()));
	}
	
	private static List<GrantedAuthority> mapToGrantedAuthorities(ProfileEnums profilesEnum) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(profilesEnum.toString()));
		return authorities;
	}
}