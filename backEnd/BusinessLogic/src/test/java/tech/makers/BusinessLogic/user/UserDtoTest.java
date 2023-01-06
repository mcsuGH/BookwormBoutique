package tech.makers.BusinessLogic.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDtoTest {
  @Test
  public void testConstructsUserDto() {
    UserDto userDto = new UserDto("user", "pass");
    assertEquals("user", userDto.getUsername());
    assertEquals("pass", userDto.getPassword());
  }
}
