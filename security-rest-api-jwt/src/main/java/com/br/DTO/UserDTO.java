package com.br.DTO;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	
	private String Authorization;
	
	private String typeAuthorization;
	
}
