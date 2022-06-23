package com.br;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SecurityRestApiJwtFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityRestApiJwtFinalApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("1234"));
	}

}
