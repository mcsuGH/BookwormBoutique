package tech.makers.BusinessLogic.user.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.makers.BusinessLogic.session.Session;
import tech.makers.BusinessLogic.session.SessionServiceImpl;
import tech.makers.BusinessLogic.user.User;
import tech.makers.BusinessLogic.user.UserRepository;

@RestController
public class AdminController {
  @Autowired
  private UserRepository repository;

  @Autowired
  private SessionServiceImpl sessionService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/api/admin/users")
  public Session createAdmin(@RequestBody User user) {
    User newUser = new User(user.getUsername(), passwordEncoder.encode(user.getPassword()), true);
    String token = sessionService.generateToken(user.getUsername());
    return new Session(newUser, token);
  }
}
