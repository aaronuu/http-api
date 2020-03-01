package com.geelytech.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.geelytech.entity.User;
import com.geelytech.util.BooleanString;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
  private Long id;
  private String name;
  private String avatar;

  private String username;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthday;
  private BigDecimal balance;
  private String admin;
  private Integer status;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime lastLoginTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime updateTime;

  private String accessToken;

  public UserDto() {
  }

  public UserDto(User user, boolean detail) {
    id = user.getId();
    name = user.getScreenName();
    avatar = user.getAvatar();

    if (detail) {
      username = user.getUsername();
      birthday = user.getBirthday();
      balance = user.getBalance();
      admin = BooleanString.toString(user.getIsAdmin() == 1);
      status = user.getStatus();
      lastLoginTime = user.getLastLoginTime();
      createTime = user.getCreateTime();
      updateTime = user.getUpdateTime();
    }
  }
}
