package com.jwt_auth.api.service;


import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt_auth.api.model.AuthenticationResponse;
import com.jwt_auth.api.model.User;
import com.jwt_auth.api.repository.UserRepository;

@Service
public class AuthenticationService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder,
			JwtService jwtService, AuthenticationManager authenticationManager) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}
	
	public AuthenticationResponse register(User request) {
		User user = new User();
		user.setFirstName(request.getFirstName());
		user.setLasttName(request.getLasttName());
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		user.setRole(request.getRole());
		
		user = userRepository.save(user);
		
		String token = jwtService.generateToken(user);
		
		return new AuthenticationResponse(token);
		
	}
	
	public AuthenticationResponse authenticate(User request) {
		authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
				);
		
		User user = userRepository.findUserByUsername(request.getUsername()).orElseThrow();
		String token = jwtService.generateToken(user);
		
		return new AuthenticationResponse(token);
	}
	
	
	
}
