package com.geelytech.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements Serializable {
  @Id
  private Long id;
  private String username;
  private String password;
  private String screenName;
  private String avatar;
  private LocalDate birthday;
  private BigDecimal balance;
  private Integer isAdmin;
  private Integer status;
  private LocalDateTime lastLoginTime;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}
