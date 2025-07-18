package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Component
public class JwtFilter extends OncePerRequestFilter {
 @Autowired 
 private JwtUtil jwtUtil;
 @Autowired 
 private UserRepository userRepository;

 @Override
 protected void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain chain) throws ServletException, IOException {
     String header = request.getHeader("Authorization");

     if (header != null && header.startsWith("Bearer")) {
         String token = header.substring(7);
         String email = jwtUtil.extractEmail(token);

         User user = userRepository.findByEmail(email).orElse(null);
         if (user != null) {
             UsernamePasswordAuthenticationToken auth =
                 new UsernamePasswordAuthenticationToken(
                     user.getEmail(), null,
                     List.of(new SimpleGrantedAuthority(user.getRole().name()))
                 );
             SecurityContextHolder.getContext().setAuthentication(auth);
         }
     }
     chain.doFilter(request, response);
 }
}

