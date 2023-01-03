package tech.makers.BusinessLogic.user;

import jakarta.persistence.*;

@Entity
@Table(name="bookworm_user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String username;

  private String password;

  private Boolean isAdmin;

  protected User() {};

  public User(String username, String password, Boolean isAdmin) {
    this.username = username;
    this.password = password;
    this.isAdmin = isAdmin;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public Boolean getIsAdmin() {
    return isAdmin;
  }

  public Long getId() {
    return id;
  }
}
