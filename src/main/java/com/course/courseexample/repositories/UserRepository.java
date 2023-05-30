package com.course.courseexample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.courseexample.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
  
}
