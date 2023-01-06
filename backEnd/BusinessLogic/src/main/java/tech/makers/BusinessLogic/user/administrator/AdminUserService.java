package tech.makers.BusinessLogic.user.administrator;

import tech.makers.BusinessLogic.session.Session;
import tech.makers.BusinessLogic.user.UserDto;

public interface AdminUserService {
  Session createAdmin(UserDto userDto);
}
