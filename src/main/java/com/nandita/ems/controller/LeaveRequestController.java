package com.nandita.ems.controller;

import com.nandita.ems.dto.leave.LeaveRequestDto;
import com.nandita.ems.dto.leave.LeaveResponse;
import com.nandita.ems.service.LeaveRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    @PostMapping
    public ResponseEntity<LeaveResponse> create(
            @Valid @RequestBody LeaveRequestDto request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(leaveRequestService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<LeaveResponse>> getAll() {
        return ResponseEntity.ok(leaveRequestService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveResponse> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(leaveRequestService.getById(id));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveResponse>> getByEmployee(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                leaveRequestService.getByEmployee(employeeId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody LeaveRequestDto request) {

        return ResponseEntity.ok(
                leaveRequestService.update(id, request)
        );
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<LeaveResponse> approve(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                leaveRequestService.approve(id)
        );
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<LeaveResponse> reject(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                leaveRequestService.reject(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        leaveRequestService.delete(id);

        return ResponseEntity.noContent().build();
    }
}