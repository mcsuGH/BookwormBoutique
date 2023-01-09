package tech.makers.BusinessLogic.user;

import jakarta.validation.constraints.NotBlank;

public class UserDto {
  @NotBlank(message = "Username is mandatory")
  private final String username;

  private final String password;

  public UserDto(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
