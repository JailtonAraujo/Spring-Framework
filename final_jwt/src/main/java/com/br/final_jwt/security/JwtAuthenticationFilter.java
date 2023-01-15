package com.br.final_jwt.security;

import com.br.final_jwt.model.User;
import com.br.final_jwt.repository.UserRepository;
import com.br.final_jwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
           @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userId;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        jwt = authHeader.substring(7);
        userId = jwtService.extractUserId(jwt);

        if(userId != null && SecurityContextHolder.getContext().getAuthentication() == null){
            User user = userRepository.findById( Long.parseLong(userId)).get();
            if(jwtService.isTokenValid(jwt,user)){
                UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(
                     user,// Setting AuthenticationPrincipal
                     null,
                     new ArrayList<>()
                );
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
