package tech.makers.BusinessLogic.user.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.makers.BusinessLogic.session.Session;
import tech.makers.BusinessLogic.user.UserDto;

@RestController
public class AdminUserController {
  @Autowired
  private AdminUserServiceImpl adminUserService;

  @PostMapping("/api/admin/users")
  public Session createAdmin(@RequestBody UserDto userDto) {
    return adminUserService.createAdmin(userDto);
  }
}
