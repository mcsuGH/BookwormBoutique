package tech.makers.BusinessLogic.session;

import java.security.Principal;

public interface SessionsService {
  String login(String username, String password);
  String generateToken(String username);
  Session createSession(SessionLogin loginForm);
  Session getSession(Principal principal, String token);
}
