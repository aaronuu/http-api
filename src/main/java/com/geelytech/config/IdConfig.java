package com.geelytech.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdConfig {
  @Bean
  public Snowflake initSnowflakeId() {
    return IdUtil.getSnowflake(1, 1);
  }
}
