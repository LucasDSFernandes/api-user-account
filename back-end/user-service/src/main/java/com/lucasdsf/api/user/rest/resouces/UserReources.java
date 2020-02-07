package com.lucasdsf.api.user.rest.resouces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.lucasdsf.api.user.domains.model.UserDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface UserReources {
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), 
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiOperation(value = "Endpoint para criar uma conta usuario")
	ResponseEntity<UserDto> createUser(@RequestBody UserDto user);

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), 
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiOperation(value = "Endpoint para obter a informação do usuario selecionado")
	ResponseEntity<UserDto> getInfoUser(String authorization, Long id);

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiOperation(value = "Endpoint para atualizar email")
	ResponseEntity<UserDto> updateEmail(String authorization, Long id, UserDto userDto);

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiOperation(value = "Endpoint para listar todos os usuarios")
	ResponseEntity<List<UserDto>> listAll(String authorization);





}
