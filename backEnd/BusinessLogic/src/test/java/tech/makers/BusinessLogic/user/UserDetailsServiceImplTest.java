package tech.makers.BusinessLogic.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.DirtiesContext;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    repository.save(new User("username", "password", false));
    UserDetails result = service.loadUserByUsername("username");
    assertEquals("username", result.getUsername());
  }

  @Test
  public void whenUserDoesNotExists_LoadUserByUsernameThrows() {
    assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("username"));
  }

  @Test
  public void createUser() {
    service.createUser(new User("username", "password", false));
    UserDetails result = service.loadUserByUsername("username");
    assertEquals("username", result.getUsername());
  }
}
