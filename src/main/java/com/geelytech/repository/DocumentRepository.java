package com.geelytech.repository;

import com.geelytech.entity.Document;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DocumentRepository extends PagingAndSortingRepository<Document, Long> {

}
