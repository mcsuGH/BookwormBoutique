package tech.makers.BusinessLogic.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.DirtiesContext;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserDetailsServiceImplTest {
  @Autowired
  private UserRepository repository;

  @Autowired
  private UserDetailsServiceImpl service;

  @Test
  public void whenUserExists_LoadUserByUsernameReturnsUser() {
    repository.save(new User("username", "password"));
    UserDetails result = service.loadUserByUsername("username");
    assertEquals("username", result.getUsername());
  }
}
