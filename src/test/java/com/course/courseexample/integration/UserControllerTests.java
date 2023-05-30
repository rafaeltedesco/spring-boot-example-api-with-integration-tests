package com.course.courseexample.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.course.courseexample.entities.User;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UserControllerTests {

  @LocalServerPort
  private int port;

  @Autowired
  TestRestTemplate restTemplate;
  
  private String getBaseUrl() {
    return "http://localhost:" + port;
  }

	@Test
	void testCreateUser() {
		User user = new User("Rafael Tedesco", "tedesco.teste@teste.com");
		ResponseEntity<User> response = restTemplate.postForEntity(getBaseUrl() + "/api/users", user, User.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		User createdUser = response.getBody();
		
		assertNotNull(createdUser);
		assertNotNull(createdUser.getId());

		assertEquals("Rafael Tedesco", createdUser.getName());
		assertEquals("tedesco.teste@teste.com", createdUser.getEmail());
	}

  @Test
  void getUserById() {
    User user = new User("Rafa Tedesco", "teste@rafatedesco.com");
    ResponseEntity<User> response = restTemplate.postForEntity(getBaseUrl() + "/api/users", user, User.class);
    User createdUser = response.getBody();

    ResponseEntity<User> getResponse = restTemplate.getForEntity(getBaseUrl() + "/api/users/" + createdUser.getId() , User.class);

    assertEquals(HttpStatus.OK, getResponse.getStatusCode());

    assertNotNull(getResponse.getBody());

    User responseUser = getResponse.getBody();
    assertEquals(createdUser.getId(), responseUser.getId());
    assertEquals(createdUser.getName(), responseUser.getName());
    assertEquals(createdUser.getEmail(), responseUser.getEmail());
    
  }
}
