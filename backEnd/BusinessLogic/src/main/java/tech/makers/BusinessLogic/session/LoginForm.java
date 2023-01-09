package tech.makers.BusinessLogic.session;

import jakarta.validation.constraints.NotBlank;

public class LoginForm {
  @NotBlank(message = "Username is mandatory")
  public String username;

  @NotBlank(message = "Password is mandatory")
  public String password;

  public LoginForm(String username, String password) {
    this.username = username;
    this.password = password;
  }
}