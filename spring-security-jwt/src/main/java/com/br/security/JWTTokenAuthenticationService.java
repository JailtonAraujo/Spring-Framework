package com.br.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.br.ApplicationContextLoad;
import com.br.model.Usuario;
import com.br.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTTokenAuthenticationService {

	//Token expiration time (1 day)
	private static final long EXPIRATION_TIME = 86400000;
	
	//Unique key to compose token
	private static final String SECRET ="c6b4bdfa-c0cd-4094-85db-61adb0b54cab";
	
	//token default prefix
	private static final String TOKEN_PREFIX ="Bearer";
	
	//Header key to identify
	private static final String HEADER_KEY = "Authorization";
	
	public void addAuthentication(HttpServletResponse response, String username) throws IOException{
		
		//Builder token
		String JWT = Jwts.builder().setSubject(username)
					.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
					.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		
		//joining token with the prefix
		String token = TOKEN_PREFIX + " " + JWT;
		
		//adding token at the response header
		response.addHeader(HEADER_KEY, token);
		
		//adding token at the response body
		response.getWriter().write("{ \"Authorization\":\""+token+"\"}");
	}
	
	//returning user valid or in case invalid user return null
	public Authentication getAuthentication(HttpServletRequest request) {
		
		//catching token send in request header
		String token = request.getHeader(HEADER_KEY);
		
		if(token != null) {
			
			//catching subject(userName) inside token 
			String clearToken = token.replace(TOKEN_PREFIX, "");
			String username = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(clearToken).getBody().getSubject();
			
			if(username != null) {
				
				//find user by userName 
				Usuario user = ApplicationContextLoad.getAplApplicationContext().getBean(UsuarioRepository.class).findByLogin(username);
				
				if(user != null) {
					return new UsernamePasswordAuthenticationToken(user.getLogin(), user.getSenha(), user.getAuthorities());
				}
				
			}
		}
		//If user invalid return null
		return null;
	}
}
