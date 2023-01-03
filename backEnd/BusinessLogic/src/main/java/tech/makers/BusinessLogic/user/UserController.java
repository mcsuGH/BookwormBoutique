package tech.makers.BusinessLogic.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @Autowired UserRepository repository;

  @PostMapping("/api/admin/users")
  public User create(@RequestBody User user) {
    return repository.save(user);
  }
}
