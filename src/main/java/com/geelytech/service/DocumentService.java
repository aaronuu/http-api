package com.geelytech.service;

import com.geelytech.common.MessageException;
import com.geelytech.common.dto.PageDto;
import com.geelytech.dto.DocumentDto;
import com.geelytech.entity.Document;
import com.geelytech.repository.DocumentRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DocumentService {

  private static final int APP_PAGE_SIZE = 10;

  @Autowired
  private DocumentRepository documentRepository;

  @Autowired
  private UserService userService;

  public DocumentDto getDocument(long id) throws MessageException {
    return toDto(get(id), true);
  }

  public PageDto<DocumentDto> listDocuments(String nextPageToken) throws MessageException {
    int currentPage = NumberUtils.toInt(nextPageToken, 1);

    //Sort sort = Sort.by(Sort.Direction.DESC, "id");
    //Pageable pageable = PageRequest.of(currentPage, APP_PAGE_SIZE, sort);
    Pageable pageable = PageRequest.of(currentPage, APP_PAGE_SIZE);
    List<DocumentDto> items = documentRepository.findAll(pageable).stream().map(d -> toDto(d, false)).collect(Collectors.toList());

    if (items.isEmpty()) {
      return PageDto.empty();
    }

    PageDto<DocumentDto> dto = PageDto.of(items);
    if (items.size() == APP_PAGE_SIZE) { // 可能有下一页
      dto.setNextPageToken(String.valueOf(currentPage + 1));
    }

    return dto;
  }

  // for web only
  public PageDto<DocumentDto> listDocuments(int currentPage, int pageSize) {
    Pageable pageable = PageRequest.of(currentPage, pageSize);
    List<DocumentDto> items = documentRepository.findAll(pageable).stream().map(d -> toDto(d, false)).collect(Collectors.toList());

    if (items.isEmpty()) {
      return PageDto.empty();
    }

    PageDto<DocumentDto> dto = PageDto.of(items);
    dto.setTotal(documentRepository.count());

    return dto;
  }

  public DocumentDto createDocument(String title, String content, BigDecimal price, Integer status) {
    Document document = new Document();
    document.setTitle(title);
    document.setContent(content);
    document.setPrice(price);
    document.setStatus(status);
    document.setCreateTime(LocalDateTime.now());

    documentRepository.save(document);

    log.info("Document {} created.", document.getId());

    return toDto(document, true);
  }

  public DocumentDto updateDocument(long id, String title, String content, BigDecimal price, Integer status) throws MessageException {
    Document document = get(id);
    document.setTitle(title);
    document.setContent(content);
    document.setPrice(price);
    document.setStatus(status);
    document.setUpdateTime(LocalDateTime.now());

    log.info("Document {} updated.", id);

    return toDto(document, true);
  }

  public void deleteDocument(long id) throws MessageException {
    Document document = get(id);

    documentRepository.delete(document);

    log.info("Document {} deleted.", id);
  }

  private Document get(long id) throws MessageException {
    Optional<Document> opt = documentRepository.findById(id);
    if (!opt.isPresent()) {
      throw new MessageException("找不到文档");
    }

    return opt.get();
  }

  private DocumentDto toDto(Document document, boolean detail) {
    DocumentDto dto = new DocumentDto(document, detail);
    dto.setOwner(userService.get(document.getOwner()));

    return dto;
  }
}
