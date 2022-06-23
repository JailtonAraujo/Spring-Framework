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

import com.br.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

/*Estabelce o gerenciador de token*/
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter{

	/*Obriga a autenticar url*/
	protected JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(url));
		
		/*Gerenciador de autenticação*/
		setAuthenticationManager(authenticationManager);
	}

	/*Retorna o usuario ao processar autenticação*/
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		//Esta pegando o token para validar
		User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
		
		
		/*Retorna o usuario, login, senha e acessos */
		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPass()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		
	}

}
