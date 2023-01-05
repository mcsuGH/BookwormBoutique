package tech.makers.BusinessLogic.session;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.makers.BusinessLogic.user.User;
import tech.makers.BusinessLogic.user.UserRepository;

import java.security.Principal;
import java.util.List;

@Service
public class SessionServiceImpl {
  public static final String TOKEN_PREFIX = "Bearer ";

  @Autowired private AuthenticationProvider authenticationManager;

  @Autowired private UserRepository userRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  public String login(String username, String password) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));
      return generateToken(username);
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid username or password");
    }
  }

  public String generateToken(String username) {
    List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList("ROLE_USER");

    String token =
        Jwts.builder()
            .setSubject(username)
            .claim("authorities", grantedAuthorities)
            .signWith(SessionSecret.getKey())
            .compact();

    return TOKEN_PREFIX + token;
  }

  public Session createSession(SessionLogin loginForm) {
    User user = userRepository.findByUsername(loginForm.username);
    String token = login(loginForm.username, loginForm.password);
    return new Session(user, token);
  }

  public Session getSession(Principal principal, String token) {
    User user = userRepository.findByUsername(principal.getName());
    return new Session(user, token);
  }
}
