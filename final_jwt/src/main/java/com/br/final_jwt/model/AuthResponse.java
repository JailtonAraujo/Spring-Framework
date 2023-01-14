package com.br.final_jwt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    public AuthResponse(User user, String token) {
       this.name = user.getName();
       this.lastname = user.getLastname();
       this.token = token;
    }

    private String name;

    private String lastname;

    private String token;

}
