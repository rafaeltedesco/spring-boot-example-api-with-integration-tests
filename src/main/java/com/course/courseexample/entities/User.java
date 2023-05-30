package com.course.courseexample.entities;

import org.hibernate.validator.constraints.Length;

import com.course.courseexample.dtos.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class User {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  public User() {}

  public User(String name, String email) {
    this.name = name;
    this.email = email;
  }

  @Length(min=3, message ="The field must be at least {min} characters")
  @NotNull(message="Name may not be null")
  private String name;

  @NotNull(message="Email may not be null")
  private String email;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  @Override
  public String toString() {
    return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
  }

  public UserDTO toDTO() {
    return new UserDTO(name, email);
  }
}
