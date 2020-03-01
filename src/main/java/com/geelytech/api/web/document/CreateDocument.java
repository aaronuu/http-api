package com.geelytech.api.web.document;

import com.geelytech.common.ApiResult;
import com.geelytech.service.DocumentService;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
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

@RestController(CreateDocument.PATH)
public class CreateDocument {
  static final String PATH = "/web/document/create-document";

  @Autowired
  private DocumentService documentService;

  @PostMapping(PATH)
  public ApiResult process(@Validated @RequestBody Request request) {
    return ApiResult.okWithData(documentService.createDocument(request.getTitle(),
        request.getContent(),
        request.getPrice(),
        request.getStatus()));
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  private static class Request {

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
  }
}
