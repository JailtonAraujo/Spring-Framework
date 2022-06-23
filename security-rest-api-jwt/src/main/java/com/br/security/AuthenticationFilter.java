package com.br.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import com.br.AplicationContextLoad;
import com.br.model.User;
import com.br.repository.UserRepository;
import com.br.service.TokenService;

public class AuthenticationFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String tokenFromHeader = getTokenHeader((HttpServletRequest) request);
		boolean tokenvalid = AplicationContextLoad.getApplicationContext().getBean(TokenService.class).isTokenValid(tokenFromHeader);
		
		if(tokenvalid) {
			this.authenticate(tokenFromHeader);
		}
		
	}
	
	
	public void authenticate (String tokenFromHeader) {
		Integer id = AplicationContextLoad.getApplicationContext().getBean(TokenService.class).getTokenId(tokenFromHeader);
		
		Optional<User> optinal = AplicationContextLoad.getApplicationContext().getBean(UserRepository.class).findById(id);
		
		if(optinal.isPresent()) {
			
			User user = optinal.get();
			
			UsernamePasswordAuthenticationToken passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, user.getPass(), user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(passwordAuthenticationToken);
		}
	}
	
	public String getTokenHeader(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			token = "";
		}
		
		return token.substring(7, token.length());
	}

	

}
