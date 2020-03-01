package com.geelytech.api.web.document;

import com.geelytech.common.ApiResult;
import com.geelytech.common.MessageException;
import com.geelytech.common.request.IdRequest;
import com.geelytech.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(DeleteDocument.PATH)
public class DeleteDocument {
  static final String PATH = "/web/document/delete-document";

  @Autowired
  private DocumentService documentService;

  @PostMapping(PATH)
  public ApiResult process(@Validated @RequestBody IdRequest request) {
    try {
      documentService.deleteDocument(request.getId());

      return ApiResult.ok();
    } catch (MessageException e) {
      return ApiResult.error(e.getMessage());
    }
  }
}
