package com.henrique.gestaofinancas.security;

import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class JWTValidateFilter extends BasicAuthenticationFilter{
    private static final String HEADER_ATRIBUTO = "Authorization";
    public static final String ATRIBUTO_PREFIXO = "Bearer ";

    public JWTValidateFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String atributo = request.getHeader(HEADER_ATRIBUTO);
        if (atributo == null){
            chain.doFilter(request, response);
            return;
        }

        if (!atributo.startsWith(ATRIBUTO_PREFIXO)){
            chain.doFilter(request,response);
            return;
        }

        String token = atributo.replace(ATRIBUTO_PREFIXO, "");

        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token, request);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request,response);

    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token, HttpServletRequest request) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(JWTAuthFilter.TOKEN_SENHA))
                                        .build()
                                        .verify(token);

            String usuario = decodedJWT.getSubject(); 

            if (usuario == null) {
                return null;
            }

            Long userId = decodedJWT.getClaim("id").asLong();
            request.setAttribute("userId", userId); 


            // Agora você pode criar um token de autenticação com o ID, se necessário
            return new UsernamePasswordAuthenticationToken(usuario, null, new ArrayList<>());
        } catch (Exception e) {
            return null; // Se a validação falhar ou o token não for válido
        }
    }

}
