package tech.makers.BusinessLogic.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @Autowired UserRepository repository;

  @PostMapping("/api/user/users")
  public User createUser(User user) {
    return repository.save(user);
  }
}
