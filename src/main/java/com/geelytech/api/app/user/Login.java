package com.geelytech.api.app.user;

import com.geelytech.common.ApiResult;
import com.geelytech.common.MessageException;
import com.geelytech.service.UserService;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(Login.PATH)
public class Login {
  static final String PATH = "/app/user/login";

  @Autowired
  private UserService userService;

  @PostMapping(PATH)
  public ApiResult process(@Validated @RequestBody Request request) {
    try {
      return ApiResult.okWithData(userService.login(request.getUsername(), request.getPassword()));
    } catch (MessageException e) {
      return ApiResult.error(e.getMessage());
    }
  }

  private static class Request {

    @NotBlank
    @Size(max = 16)
    private String username;

    @NotBlank
    @Size(max = 40)
    private String password;

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }
}
