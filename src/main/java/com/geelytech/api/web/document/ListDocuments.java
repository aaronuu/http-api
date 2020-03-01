package com.geelytech.api.web.document;

import com.geelytech.common.ApiResult;
import com.geelytech.common.request.PageRequest;
import com.geelytech.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(ListDocuments.PATH)
public class ListDocuments {
  static final String PATH = "/web/document/list-documents";

  @Autowired
  private DocumentService documentService;

  @PostMapping(PATH)
  public ApiResult process(@Validated @RequestBody PageRequest request) {
    return ApiResult.okWithData(documentService.listDocuments(request.getCurrentPage(), request.getPageSize()));
  }
}
