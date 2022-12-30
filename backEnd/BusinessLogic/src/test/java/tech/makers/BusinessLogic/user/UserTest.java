package tech.makers.BusinessLogic.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
  @Test
  public void testConstructs() {
    Collection<? extends GrantedAuthority> mockAuthorities = new ArrayList<GrantedAuthority>();
    User user = new User("username", "password");
    user.setAuthorities(mockAuthorities);

    assertNull(user.getId());
    assertEquals("username", user.getUsername());
    assertEquals("password", user.getPassword());
    assertEquals(mockAuthorities, user.getAuthorities());
    assertTrue(user.isAccountNonExpired());
    assertTrue(user.isAccountNonLocked());
    assertTrue(user.isCredentialsNonExpired());
    assertTrue(user.isEnabled());
  }
}
