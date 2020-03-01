package com.geelytech.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.geelytech.entity.Document;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DocumentDto {
  private Long id;
  private UserDto owner;
  private String title;
  private String content;
  private BigDecimal price;
  private Integer status;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime updateTime;

  public DocumentDto() {
  }

  public DocumentDto(Document document, boolean detail) {
    id = document.getId();
    title = document.getTitle();
    price = document.getPrice();
    status = document.getStatus();
    updateTime = document.getUpdateTime();

    if (detail) {
      content = document.getContent();
    }
  }
}
