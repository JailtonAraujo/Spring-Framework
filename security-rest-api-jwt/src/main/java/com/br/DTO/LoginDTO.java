package com.br.DTO;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String pass;

}
