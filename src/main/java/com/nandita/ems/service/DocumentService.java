package com.nandita.ems.service;

import com.nandita.ems.dto.document.DocumentRequest;
import com.nandita.ems.dto.document.DocumentResponse;
import org.springframework.data.domain.Page;

public interface DocumentService {

    DocumentResponse create(DocumentRequest request);

    DocumentResponse update(Long id, DocumentRequest request);

    DocumentResponse getById(Long id);

    Page<DocumentResponse> getAll(int page, int size);

    void delete(Long id);
}