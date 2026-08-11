package com.nandita.ems.controller;

import com.nandita.ems.dto.document.DocumentRequest;
import com.nandita.ems.dto.document.DocumentResponse;
import com.nandita.ems.service.DocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping
    public ResponseEntity<DocumentResponse> create(
            @Valid @RequestBody DocumentRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(documentService.create(request));
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(documentService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponse> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(documentService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody DocumentRequest request) {

        return ResponseEntity.ok(
                documentService.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        documentService.delete(id);

        return ResponseEntity.noContent().build();
    }
}