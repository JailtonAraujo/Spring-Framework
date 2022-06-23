package com.br.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDTO {

	private String Autorization;
	private String type;
	private String nameUser;
	
}
