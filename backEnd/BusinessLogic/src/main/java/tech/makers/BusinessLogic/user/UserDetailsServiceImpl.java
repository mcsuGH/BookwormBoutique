package tech.makers.BusinessLogic.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private UserRepository repository;

  public UserDetails loadUserByUsername(String username) {
    User user = repository.findByUsername(username);
    return user;
  }
}
