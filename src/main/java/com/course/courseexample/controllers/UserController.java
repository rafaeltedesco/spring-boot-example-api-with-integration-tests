package com.course.courseexample.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.courseexample.dtos.UserDTO;
import com.course.courseexample.entities.User;
import com.course.courseexample.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
  
  @Autowired
  private UserRepository userRepository;

  @PostMapping
  public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) {
    User user = userDTO.toEntity();
    User userWithId = this.userRepository.save(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(userWithId);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> findById(@PathVariable Long id) {
    User user = this.userRepository.findById(id).orElseThrow();
    return ResponseEntity.ok().body(user);
  }

  @GetMapping
  public ResponseEntity<List<User>> findAll() {
    List<User> users = this.userRepository.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(users);
  }
}
