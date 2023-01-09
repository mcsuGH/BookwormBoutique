package tech.makers.BusinessLogic.session.administrator;

import tech.makers.BusinessLogic.session.LoginForm;
import tech.makers.BusinessLogic.session.Session;

import java.security.Principal;

public interface AdminSessionService {
  String login(String username, String password);
  String generateToken(String username);
  Session createSession(LoginForm loginForm);
  Session getSession(Principal principal, String token);
}
