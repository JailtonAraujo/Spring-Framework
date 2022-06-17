package com.br.model;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Entity(name = "role")
@Data
public class Role implements GrantedAuthority{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String nomeRole;

	@Override
	public String getAuthority() {
		return nomeRole;
	}
	
	
}
