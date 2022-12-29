package tech.makers.BusinessLogic.user;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
  @Test
  public void testConstructs() {
    User user = new User("username", "password");
    assertNull(user.getId());
    assertEquals("username", user.getUsername());
    assertEquals("password", user.getPassword());
    assertTrue(user.isAccountNonExpired());
    assertTrue(user.isAccountNonLocked());
    assertTrue(user.isCredentialsNonExpired());
    assertTrue(user.isEnabled());
  }
}
