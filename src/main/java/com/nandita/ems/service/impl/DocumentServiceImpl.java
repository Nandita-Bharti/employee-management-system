package com.nandita.ems.service.impl;

import com.nandita.ems.dto.document.DocumentRequest;
import com.nandita.ems.dto.document.DocumentResponse;
import com.nandita.ems.entity.Document;
import com.nandita.ems.entity.Employee;
import com.nandita.ems.mapper.DocumentMapper;
import com.nandita.ems.repository.DocumentRepository;
import com.nandita.ems.repository.EmployeeRepository;
import com.nandita.ems.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final EmployeeRepository employeeRepository;
    private final DocumentMapper documentMapper;

    @Override
    public DocumentResponse create(DocumentRequest request) {

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Document document = documentMapper.toEntity(request);

        document.setEmployee(employee);

        Document saved = documentRepository.save(document);

        return documentMapper.toResponse(saved);
    }

    @Override
    public DocumentResponse update(Long id, DocumentRequest request) {

        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        document.setEmployee(employee);
        document.setDocumentType(request.getDocumentType());
        document.setFileName(request.getFileName());
        document.setFileUrl(request.getFileUrl());

        Document updated = documentRepository.save(document);

        return documentMapper.toResponse(updated);
    }

    @Override
    public DocumentResponse getById(Long id) {

        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        return documentMapper.toResponse(document);
    }

    @Override
    public Page<DocumentResponse> getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return documentRepository.findAll(pageable)
                .map(documentMapper::toResponse);
    }

    @Override
    public void delete(Long id) {

        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        documentRepository.delete(document);
    }
}