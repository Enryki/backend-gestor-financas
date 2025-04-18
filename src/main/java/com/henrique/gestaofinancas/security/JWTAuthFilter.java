package com.henrique.gestaofinancas.security;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henrique.gestaofinancas.users.User;
import com.henrique.gestaofinancas.users.UserData;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;


import org.springframework.web.filter.OncePerRequestFilter;

public class JWTAuthFilter extends OncePerRequestFilter {  // Alterado para OncePerRequestFilter
    
    private final AuthenticationManager authenticationManager;
    private static final int TOKEN_EXPIRACAO = 600_000;
    public static final String TOKEN_SENHA = "1e42f221-69bb-46b1-a057-a2aecfb21238";

    public JWTAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, 
                                  @NonNull HttpServletResponse response, 
                                  @NonNull FilterChain filterChain) throws ServletException, IOException {
        
        if (!request.getServletPath().equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    user.getUsername(), 
                    user.getPassword()
                )
            );
            
            UserData userData = (UserData) authentication.getPrincipal();
            String token = JWT.create()
                .withSubject(userData.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
                .sign(Algorithm.HMAC512(TOKEN_SENHA));

            Map<String, String> tokenResponse = new HashMap<>();
            tokenResponse.put("token", token);
            
            // Configure o content-type
            response.setContentType("application/json");
            
            // Escreva o JSON na resposta
            new ObjectMapper().writeValue(response.getWriter(), tokenResponse);
            
        } catch (AuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Falha na autenticação");
            new ObjectMapper().writeValue(response.getWriter(), errorResponse);
        }
    }
}