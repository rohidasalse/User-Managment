package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
 @Autowired 
 private UserRepository userRepository;

 @GetMapping
 @PreAuthorize("hasRole('ADMIN')")
 public List<User> getAll() {
     return userRepository.findAll();
 }
 
 

 @GetMapping("/{id}")
 public ResponseEntity<?> getUser(@PathVariable Long id) {
     return ResponseEntity.ok(userRepository.findById(id));

 }

 
 @DeleteMapping("/{id}")
 @PreAuthorize("hasRole('ADMIN')")
 public ResponseEntity<String> deleteUser(@PathVariable Long id) {
     userRepository.deleteById(id);
     return new ResponseEntity<String>("user deleted : "+id,HttpStatus.OK);
 }
}

