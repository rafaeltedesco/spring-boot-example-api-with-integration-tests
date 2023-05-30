package com.course.courseexample.dtos;

import org.hibernate.validator.constraints.Length;

import com.course.courseexample.entities.User;

import jakarta.validation.constraints.NotNull;

public class UserDTO {
  
  @Length(min=3, message ="The field must be at least {min} characters")
  @NotNull(message="Name may not be null")
  String name;

  @NotNull(message="Email may not be null")
  String email;

  public UserDTO() {}

  public UserDTO(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public User toEntity() {
    return new User(name, email);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
