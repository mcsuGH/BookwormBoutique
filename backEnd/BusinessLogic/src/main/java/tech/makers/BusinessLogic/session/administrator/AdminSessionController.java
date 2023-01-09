package tech.makers.BusinessLogic.session.administrator;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.makers.BusinessLogic.session.LoginForm;
import tech.makers.BusinessLogic.session.Session;

import java.security.Principal;

@RestController
public class AdminSessionController {
  @Autowired
  private AdminSessionServiceImpl adminSessionService;

  @PostMapping("/api/admin/session")
  public Session create(@RequestBody @Valid LoginForm loginForm) {
    return adminSessionService.createSession(loginForm);
  }

  @GetMapping("/api/admin/session")
  public Session get(Principal principal, @RequestHeader("authorization") String token) {
    return adminSessionService.getSession(principal, token);
  }
}
