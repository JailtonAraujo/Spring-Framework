package com.br;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SecurityRestApiJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityRestApiJwtApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}

}
