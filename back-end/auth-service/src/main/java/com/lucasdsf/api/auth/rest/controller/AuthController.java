package com.lucasdsf.api.auth.rest.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasdsf.api.auth.rest.request.JwtAuthenticationRequest;
import com.lucasdsf.api.auth.rest.resource.AuthResource;
import com.lucasdsf.api.auth.rest.response.JwtAuthenticationResponse;
import com.lucasdsf.api.auth.utils.JwtTokenUtil;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class AuthController implements AuthResource{

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	private static final String TOKEN_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	@PostMapping(value = "/token")
	public ResponseEntity<JwtAuthenticationResponse> gerarTokenJwt(JwtAuthenticationRequest authenticationDto){
		JwtAuthenticationResponse response = new JwtAuthenticationResponse();

		log.info("Gerando token para o documento {}.", authenticationDto.getLogin());
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDto.getLogin(), authenticationDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDto.getLogin());
		String token = jwtTokenUtil.obterToken(userDetails);
		response.setAccesToken(token);
		response.setExpiresIn(Long.toString(jwtTokenUtil.getExpirationDateFromToken(token).getTime()));
		response.setTokenType(BEARER_PREFIX);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@GetMapping(value = "/refresh")
	public ResponseEntity<JwtAuthenticationResponse> gerarRefreshTokenJwt(HttpServletRequest request) {
		log.info("Gerando refresh token JWT.");
		JwtAuthenticationResponse response = new JwtAuthenticationResponse();
		Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));
		
		if (token.isPresent() && token.get().startsWith(BEARER_PREFIX)) {
			token = Optional.of(token.get().substring(7));
        }
		if (!token.isPresent()) {
			response.getErrors().add("Token não informado.");
		} else if (!jwtTokenUtil.tokenValido(token.get())) {
			response.getErrors().add("Token inválido ou expirado.");
		}
		if (!response.getErrors().isEmpty()) { 
			return ResponseEntity.badRequest().body(response);
		}
		
		String refreshedToken = jwtTokenUtil.refreshToken(token.get());
		response.setAccesToken(refreshedToken);
		response.setExpiresIn(Long.toString(jwtTokenUtil.getExpirationDateFromToken(token.get()).getTime()));
		response.setTokenType(BEARER_PREFIX);
		return ResponseEntity.ok(response);
	}

}
