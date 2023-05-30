package com.course.courseexample.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.course.courseexample.entities.User;
import com.course.courseexample.repositories.UserRepository;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UserControllerTests {

  @LocalServerPort
  private int port;

  @Autowired
  TestRestTemplate restTemplate;

  @Autowired
  private UserRepository userRepository;
  
  private String getBaseUrl() {
    return "http://localhost:" + port + "/api/users";
  }

  private List<User> users = new ArrayList<>(Arrays.asList(
    new User("John Doe", "johndoe@test.com"), 
   new User("Alexane McDermott", "alexane_mcdermott@test.com")
 ));

  @BeforeEach
  void setupDatabase() {
    this.userRepository.deleteAll(); 
    this.userRepository.saveAll(users);
  }

	@Test
	void testCreateUser() {
		User user = new User("Rafael Tedesco", "tedesco.teste@teste.com");
		ResponseEntity<User> response = restTemplate.postForEntity(getBaseUrl(), user, User.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		User createdUser = response.getBody();
		
		assertNotNull(createdUser);
		assertNotNull(createdUser.getId());

		assertEquals("Rafael Tedesco", createdUser.getName());
		assertEquals("tedesco.teste@teste.com", createdUser.getEmail());
	}


  @Test
  void testGetUsers() {
    ResponseEntity<List<User>> response = restTemplate.exchange(
      getBaseUrl(), HttpMethod.GET, null, 
      new ParameterizedTypeReference<List<User>>() {});
  
    assertEquals(HttpStatus.OK, response.getStatusCode());

    assertEquals(users.size(), response.getBody().size());
    assertEquals(users, response.getBody());
  }

  @Test
  void testGetUserById() {
    User user = new User("Rafa Tedesco", "teste@rafatedesco.com");
    ResponseEntity<User> response = restTemplate.postForEntity(getBaseUrl(), user, User.class);
    User createdUser = response.getBody();

    ResponseEntity<User> getResponse = restTemplate.getForEntity(getBaseUrl() + "/" + createdUser.getId() , User.class);

    assertEquals(HttpStatus.OK, getResponse.getStatusCode());

    assertNotNull(getResponse.getBody());

    User responseUser = getResponse.getBody();
    assertEquals(createdUser.getId(), responseUser.getId());
    assertEquals(createdUser.getName(), responseUser.getName());
    assertEquals(createdUser.getEmail(), responseUser.getEmail());
  }
}
