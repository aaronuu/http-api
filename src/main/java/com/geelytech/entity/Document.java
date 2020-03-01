package com.geelytech.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class Document implements Serializable {
  @Id
  private Long id;
  private Long owner;
  private String title;
  private String content;
  private BigDecimal price;
  private Integer status;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}
