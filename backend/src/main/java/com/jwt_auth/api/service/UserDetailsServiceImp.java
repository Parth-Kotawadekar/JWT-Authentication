package com.jwt_auth.api.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt_auth.api.repository.UserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService{

	private final UserRepository userRepository;
	
	
	
	public UserDetailsServiceImp(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userRepository.findUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
	}

}
