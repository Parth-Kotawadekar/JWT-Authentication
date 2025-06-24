package com.jwt_auth.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt_auth.api.model.AuthenticationResponse;
import com.jwt_auth.api.model.User;
import com.jwt_auth.api.service.AuthenticationService;

@RestController
public class AuthenticationController {

	private final AuthenticationService authService;

	public AuthenticationController(AuthenticationService authService) {
		super();
		this.authService = authService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody User request){
		return ResponseEntity.ok(authService.register(request));
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody User request){
		return ResponseEntity.ok(authService.authenticate(request));
	}
}
