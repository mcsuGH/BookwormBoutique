package tech.makers.BusinessLogic.user.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.makers.BusinessLogic.session.Session;
import tech.makers.BusinessLogic.session.SessionServiceImpl;
import tech.makers.BusinessLogic.user.User;
import tech.makers.BusinessLogic.user.UserDto;
import tech.makers.BusinessLogic.user.UserRepository;

@Service
public class AdminUserServiceImpl implements AdminUserService {
  @Autowired
  private UserRepository repository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private SessionServiceImpl sessionService;

  public Session createAdmin(UserDto userDto) {
    User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), true);
    repository.save(user);
    String token = sessionService.generateToken(user.getUsername());
    return new Session(user, token);
  }
}
