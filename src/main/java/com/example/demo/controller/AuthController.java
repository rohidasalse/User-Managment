package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.EmailAndPasswordNullPointerException;
import com.example.demo.exception.UserDtoNullPointerException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.JwtUtil;


@RestController
public class AuthController {
 @Autowired private UserRepository userRepository;
 @Autowired private PasswordEncoder encoder;
 @Autowired private JwtUtil jwtUtil;

 @PostMapping("/register")
 public ResponseEntity<?> register(@RequestBody User user) {
	  if(user == null) {
          throw new UserDtoNullPointerException("User data cannot be null");
      }
     user.setPassword(encoder.encode(user.getPassword()));
     if (user.getRole() == null) user.setRole(Role.ROLE_USER);
     return ResponseEntity.ok(userRepository.save(user));
     
 }

 @PostMapping("/login")
 public ResponseEntity<?> login(@RequestBody AuthRequest request) {
   
     User user = userRepository.findByEmail(request.getEmail())
         .orElseThrow(() -> new UsernameNotFoundException("User with email " + request.getEmail() + " not found"));

     if(request.getEmail() == null || request.getPassword() == null) {
         throw new EmailAndPasswordNullPointerException("Email and password cannot be null");
     }
     if (!encoder.matches(request.getPassword(), user.getPassword())) {
         throw new BadCredentialsException("Invalid credentials: wrong password");
     }

   
     String token = jwtUtil.generateToken(user);
     return ResponseEntity.ok(new AuthResponse(token));
 }

}
