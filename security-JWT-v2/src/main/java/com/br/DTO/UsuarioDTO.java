package com.br.DTO;

import java.io.Serializable;
import java.util.Set;

import com.br.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;
	
	private Set<Role> perfis;
	
	private String token;
	
	private String typeToken;
}
