package com.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.AplicationContextLoad;
import com.br.DTO.LoginDTO;
import com.br.DTO.UserDTO;
import com.br.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<UserDTO> auth(@RequestBody LoginDTO loginDTO){
		UsernamePasswordAuthenticationToken passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPass());
		
		Authentication authentication = AplicationContextLoad.getApplicationContext().getBean(AuthenticationManager.class).authenticate(passwordAuthenticationToken);
		
		UserDTO userAuthenticate = tokenService.generateToken(authentication);
		
		return ResponseEntity.ok(userAuthenticate);
	}
}
