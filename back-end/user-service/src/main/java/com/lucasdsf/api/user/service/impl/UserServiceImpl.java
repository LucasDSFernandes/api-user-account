package com.lucasdsf.api.user.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.lucasdsf.api.user.builder.ProfileUserBuilder;
import com.lucasdsf.api.user.builder.UserDtoBuilder;
import com.lucasdsf.api.user.builder.UserEntityBuilder;
import com.lucasdsf.api.user.domains.entity.ProfileUser;
import com.lucasdsf.api.user.domains.entity.User;
import com.lucasdsf.api.user.domains.model.UserDto;
import com.lucasdsf.api.user.domains.repository.ProfileRepository;
import com.lucasdsf.api.user.domains.repository.UserRepository;
import com.lucasdsf.api.user.enums.ProfileEnums;
import com.lucasdsf.api.user.exception.ErrorException;
import com.lucasdsf.api.user.service.UserService;
import com.lucasdsf.api.user.util.PasswordUtil;

@Component
public class UserServiceImpl implements UserService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		ProfileUser profile = new ProfileUser();
		
		Optional<User> user = userRepository.findByUserLogin(userDto.getLogin());
		if(user.isPresent()) {
			 throw new ErrorException("Login Usuario ja existe");
		}
			
		profile = profileRepository.findByDescriptionProfile(ProfileEnums.ROLE_USER);
		
		if(!Optional.ofNullable(profile).isPresent()) {
			profile = ProfileUserBuilder.userDTOBuilder()
											.profile(ProfileEnums.ROLE_USER).build();
			logger.info("Inserindo perfil {} ao usuario {}", userDto.getLogin(), ProfileEnums.ROLE_USER);
			profileRepository.save(profile);
		}

		logger.info("Inserindo usuario login: {}, nome: {}", userDto.getLogin(), getFirstName(userDto.getFullName()));
		this.userRepository.save(UserEntityBuilder.userBuilder().email(userDto.getEmail())
										.login(userDto.getLogin())
										.profile(profile)
										.password(PasswordUtil.generateBCrypt(userDto.getPassword()))
										.firstName(getFirstName(userDto.getFullName()))
										.userFull(userDto.getFullName())
										.build());
		return userDto;
	}
	
	@Override
	public UserDto getInfoUser(Long id) {
		User userEntity = findUser(id, null);
		return UserDtoBuilder.userDTOBuilder()
				.email(userEntity.getEmail())
				.fullName(userEntity.getFullName())
				.login(userEntity.getUserLogin())
				.build();
	}

	@Override
	public UserDto updateUserEmail(Long id, UserDto userDto) {
		User userEntity = findUser(id, userDto);
		userEntity.setEmail(userDto.getEmail());
	
		logger.info("Atualizando email do login: {}, email: {}", userDto.getLogin(), userDto.getEmail());
		userRepository.save(userEntity);
		
		userDto.setMessage("Email alterado para "+userDto.getEmail());
		userDto.setUpdated(true);
		return userDto;
	}
	

	@Override
	public List<UserDto> listAll() {
		List<UserDto> userList = new ArrayList<>();
		List<User> userEntity = userRepository.findAll();
		userEntity.stream()
				.sorted(Comparator.comparing(User::getFirstName, Comparator.nullsLast(Comparator.naturalOrder())))
				.forEach(user ->  
					userList.add(UserDtoBuilder.userDTOBuilder()
							.id(user.getId())
							.email(user.getEmail())
							.fullName(user.getFullName())
							.login(user.getUserLogin())
							.build())
				);
		return userList;
	}

	private String getFirstName(String fullName) {
	      String pattern = "\\S+";
	      String firtName ="";
	      Pattern r = Pattern.compile(pattern);
	      Matcher m = r.matcher(fullName);
	      if (m.find( )) {
	        firtName = m.group(0);
	      }else {
	    	firtName = fullName;
	      }
	      return firtName;
	}
	
	@Cacheable(value = "users-account", key = "#id")
	private User findUser(Long id, UserDto userDto) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ErrorException("Usuario não encontrado."));
	}
}
