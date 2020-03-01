package com.geelytech.api.app.document;

import com.geelytech.common.ApiResult;
import com.geelytech.common.MessageException;
import com.geelytech.common.request.LoadMoreRequest;
import com.geelytech.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(ListDocuments.PATH)
public class ListDocuments {
  static final String PATH = "/app/document/list-documents";

  @Autowired
  private DocumentService documentService;

  @PostMapping(PATH)
  public ApiResult process(@Validated @RequestBody LoadMoreRequest request) {
    try {
      return ApiResult.okWithData(documentService.listDocuments(request.getNextPageToken()));
    } catch (MessageException e) {
      return ApiResult.error(e.getMessage());
    }
  }
}
