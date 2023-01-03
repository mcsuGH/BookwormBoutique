package tech.makers.BusinessLogic.user;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
  @Test
  public void createUser() {
    User user = new User("user", "pass", false);
    assertEquals("user", user.getUsername());
    assertEquals("pass", user.getPassword());
    assertEquals(false, user.getIsAdmin());
  }
}
