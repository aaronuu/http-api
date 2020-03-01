package com.geelytech.api.web.user;

import com.geelytech.common.ApiResult;
import com.geelytech.common.MessageException;
import com.geelytech.service.UserService;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(Login.PATH)
public class Login {
  static final String PATH = "/web/user/login";

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

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  private static class Request {

    @NotBlank
    @Size(max = 16)
    private String username;

    @NotBlank
    @Size(max = 40)
    private String password;
  }
}
