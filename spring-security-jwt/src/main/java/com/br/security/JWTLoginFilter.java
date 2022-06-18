package com.br.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.br.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

//token Manager
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter{

	//establish token manager
	protected JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(url));
		
		//Authentication manager
		setAuthenticationManager(authenticationManager);
	}

	//Return user after processing authentication
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		//Catching to validate
		Usuario user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
		
		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getSenha()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
			new JWTTokenAuthenticationService().addAuthentication(response, authResult.getName());
	}

}
