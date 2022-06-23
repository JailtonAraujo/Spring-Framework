package com.br.service;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.br.DTO.UserDTO;
import com.br.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	private static final long EXPIRATION_TIME = 172800000;

	private static final String SECRET = "b2cc3dcf-817f-4c34-a58c-8ef631a3a12e";

	private static final String TOKEN_PREFIX = "Bearer";

	private static final String HEADER_STRING = "Authorization";
	
	public UserDTO generateToken(Authentication authentication) {
		
		User user = (User) authentication.getPrincipal();
		
		Date now = new Date();
		Date exp = new Date(now.getTime() + EXPIRATION_TIME);
		
		String JWT = Jwts.builder().setIssuer("myapp")
		.setSubject(user.getId().toString())
		.setIssuedAt(now)
		.setExpiration(exp)
		.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		UserDTO userDTO = UserDTO.builder().name(user.getName()).Authorization(JWT).typeAuthorization(TOKEN_PREFIX).build();
		
		return userDTO;
	}
	
	public Integer getTokenId(String token) {
		Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		
		return Integer.valueOf(body.getSubject());
	}
	
	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
