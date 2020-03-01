package com.geelytech.api.web.document;

import com.geelytech.common.ApiResult;
import com.geelytech.common.MessageException;
import com.geelytech.common.request.IdRequest;
import com.geelytech.service.DocumentService;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UpdateDocument {
  static final String PATH = "/web/document/update-document";

  @Autowired
  private DocumentService documentService;

  @PostMapping(PATH)
  public ApiResult process(@Validated @RequestBody Request request) {
    try {
      return ApiResult.okWithData(documentService.updateDocument(request.getId(),
          request.getTitle(),
          request.getContent(),
          request.getPrice(),
          request.getStatus()));
    } catch (MessageException e) {
      return ApiResult.error(e.getMessage());
    }
  }

  private static class Request extends IdRequest {

    @NotBlank
    @Size(max = 32)
    private String title;

    @NotBlank
    @Size(max = 1024)
    private String content;

    @PositiveOrZero
    private BigDecimal price;

    @PositiveOrZero
    private Integer status;

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }

    public BigDecimal getPrice() {
      return price;
    }

    public void setPrice(BigDecimal price) {
      this.price = price;
    }

    public Integer getStatus() {
      return status;
    }

    public void setStatus(Integer status) {
      this.status = status;
    }
  }
}
