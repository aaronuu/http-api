package com.geelytech.api.app.document;

import com.geelytech.common.ApiResult;
import com.geelytech.common.MessageException;
import com.geelytech.common.request.IdRequest;
import com.geelytech.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(GetDocument.PATH)
public class GetDocument {
  static final String PATH = "/app/document/get-document";

  @Autowired
  private DocumentService documentService;

  @PostMapping(PATH)
  public ApiResult process(@Validated @RequestBody IdRequest request) {
    try {
      return ApiResult.okWithData(documentService.getDocument(request.getId()));
    } catch (MessageException e) {
      return ApiResult.error(e.getMessage());
    }
  }
}
