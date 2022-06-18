package com.br.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

//where all requests are captured for authentication
public class JWTApiAutheticationFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//Establish authentication for request
		Authentication authentication = new JWTTokenAuthenticationService().getAuthentication((HttpServletRequest)request);
		
		//Puts process authentication at the spring security
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//Continue authentication process
		chain.doFilter(request, response);
		
	}

}
