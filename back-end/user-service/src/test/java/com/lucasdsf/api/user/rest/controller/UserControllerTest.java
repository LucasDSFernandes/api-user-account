package com.lucasdsf.api.user.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.lucasdsf.api.user.domains.model.UserDto;
import com.lucasdsf.api.user.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {

	@MockBean
	private UserServiceImpl userService;

	@Autowired
	private MockMvc mvc;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@WithMockUser
	void testCreateUser() throws Exception{
		BDDMockito.given(this.userService.createUser(Mockito.any(UserDto.class))).willReturn(Mockito.any(UserDto.class));
		mvc.perform(post("/user/")			
				.content(getUserDtoContent())
				.contentType(MediaType.APPLICATION_JSON)
		        .accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	private String getUserDtoContent() throws JsonProcessingException {
		UserDto request = getJsonToUserDto();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return mapper.writeValueAsString(request);
	}

	private UserDto getJsonToUserDto() {
		return new Gson().fromJson(jsonToObjectUser(), UserDto.class);
	}

	private String jsonToObjectUser() {
		String json = "	{\r\n" + 
				"        \"fullName\": \"LUCAS DOS SANTOS FERNANDES\",\r\n" + 
				"        \"firstName\": null,\r\n" + 
				"        \"email\": \"tesd@gmail.com\",\r\n" + 
				"        \"password\": null,\r\n" + 
				"        \"login\": \"adminOutro\",\r\n" + 
				"        \"message\": null,\r\n" + 
				"        \"updated\": false,\r\n" + 
				"        \"id\": 10\r\n" + 
				"    }";
		return json;
	}
}
