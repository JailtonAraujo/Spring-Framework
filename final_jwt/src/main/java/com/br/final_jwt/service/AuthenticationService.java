package com.br.final_jwt.service;

import com.br.final_jwt.model.AuthResponse;
import com.br.final_jwt.model.User;
import com.br.final_jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var userSaved = repository.save(user);

        var jwtToken = jwtService.generateToken(userSaved);//passando a id do usuario para o subject do token

        AuthResponse authResponse = new AuthResponse(user,jwtToken);

        return authResponse;
    }

    public AuthResponse authenticate(User userRequest) {

        System.out.println(userRequest.getUsername() +""+userRequest.getPassword());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                       userRequest.getUsername(),
                        userRequest.getPassword()
                )
        );
        var user = repository.findByUsername(userRequest.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);//passando a id do usuario para o subject do token

        AuthResponse authResponse = new AuthResponse(user,jwtToken);

        return authResponse;
    }

}
