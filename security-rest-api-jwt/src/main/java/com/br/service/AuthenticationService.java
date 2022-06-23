package com.br.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.model.User;
import com.br.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Optional<User> user = userRepository.findByUsername(username);
		
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new UsernameNotFoundException("User not found!");
	}

}
