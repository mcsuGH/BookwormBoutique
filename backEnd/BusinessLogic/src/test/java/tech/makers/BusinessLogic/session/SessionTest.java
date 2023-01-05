package tech.makers.BusinessLogic.session;

import org.junit.jupiter.api.Test;
import tech.makers.BusinessLogic.user.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SessionTest {
  @Test
  void testConstructs() {
    User user = new User("username", "password", false);
    Session subject = new Session(user, "token");
    assertEquals(user, subject.getUser());
    assertEquals("token", subject.getToken());
  }
}
