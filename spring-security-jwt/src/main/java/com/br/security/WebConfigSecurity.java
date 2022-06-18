package com.br.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.br.service.ImplementacaoUserDatailsService;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
	private ImplementacaoUserDatailsService implementacaoUserDatailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//Enabling protection against users who are not token validated
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		
		//Enabling home page access
		.disable().authorizeRequests().antMatchers("/").permitAll()
		.antMatchers("/index").permitAll()
		
		//Logout URL
		.anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
		
		//Mapped logout URL and invalidates user
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		
		//Filter login requests for authentication 
		.and().addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
		
		//filter out too many requests to check for the presence of the JWT token in the HTTP header.
		.addFilterBefore(new JWTApiAutheticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//Service that will consult user in data base
		auth.userDetailsService(implementacaoUserDatailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
