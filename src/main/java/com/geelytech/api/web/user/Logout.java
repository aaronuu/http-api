package com.geelytech.api.web.user;

import com.geelytech.common.ApiResult;
import com.geelytech.config.RequestHeaders;
import com.geelytech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController(Logout.PATH)
public class Logout {
  static final String PATH = "/web/user/logout";

  @Autowired
  private UserService userService;

  @PostMapping(PATH)
  public ApiResult process(@RequestHeader(value = RequestHeaders.ACCESS_TOKEN, required = false) String accessToken) {
    userService.logout(accessToken);

    return ApiResult.ok();
  }
}
