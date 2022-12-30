package tech.makers.BusinessLogic.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
  @Test
  public void testConstructs() {
    User user = new User("username", "password", true);

    assertNull(user.getId());
    assertEquals("username", user.getUsername());
    assertEquals("password", user.getPassword());
    assertTrue(user.isAccountNonExpired());
    assertTrue(user.isAccountNonLocked());
    assertTrue(user.isCredentialsNonExpired());
    assertTrue(user.isEnabled());
  }
}
