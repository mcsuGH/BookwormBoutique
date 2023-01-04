package tech.makers.BusinessLogic.user.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.makers.BusinessLogic.user.User;
import tech.makers.BusinessLogic.user.UserRepository;

@RestController
public class AdminController {
  @Autowired
  UserRepository repository;

  @PostMapping("/api/admin/users")
  public User createAdmin(@RequestBody User user) {
    return repository.save(user);
  }
}
