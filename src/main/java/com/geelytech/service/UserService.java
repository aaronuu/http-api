package com.geelytech.service;

import com.geelytech.common.MessageException;
import com.geelytech.dto.UserDto;
import com.geelytech.entity.User;
import com.geelytech.repository.UserRepository;
import com.google.common.base.Strings;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public UserDto login(String username, String password) throws MessageException {
    // check
    User user = userRepository.findUserByUsernameAndPassword(username, password);
    if (null == user) {
      throw new MessageException("用户名或密码错误");
    }

    log.info("User {} login.", username);

    UserDto dto = new UserDto(user, true);
    dto.setAccessToken(RandomStringUtils.randomAlphanumeric(40));

    return dto;
  }

  public void logout(String accessToken) {
    if (!Strings.isNullOrEmpty(accessToken)) {
      // clear session

      log.info("User with access token {} logout.", accessToken);
    }
  }

  public UserDto get(long id) {
    Optional<User> opt = userRepository.findById(id);
    if (!opt.isPresent()) {
      UserDto dto = new UserDto();
      dto.setId(id);
      dto.setName("已重置");
      return dto;
    }

    return new UserDto(opt.get(), false);
  }
}
