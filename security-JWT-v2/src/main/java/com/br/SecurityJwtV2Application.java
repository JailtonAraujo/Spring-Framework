package com.br;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SecurityJwtV2Application {
	
	public static void main(String[] args) {
		SpringApplication.run(SecurityJwtV2Application.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}

}
