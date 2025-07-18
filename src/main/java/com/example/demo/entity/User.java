package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;


@Entity
@Data
public class User {
 @Id
 @GeneratedValue
 private Long id;
 private String name;
 private String email;
 private String password;

 @Enumerated(EnumType.STRING)
 private Role role;
}

