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

    public AuthResponse register(User request) {

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        var userCreated = repository.save(request);

        var jwtToken = jwtService.generateToken(userCreated);

        var authResponse = new AuthResponse(userCreated,jwtToken);

        return authResponse;
    }

    public AuthResponse authenticate(User request) {



            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );


        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        var authResponse = new AuthResponse(user,jwtToken);

        return authResponse;

    }
}
