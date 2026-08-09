package com.nandita.ems.service;

import com.nandita.ems.dto.department.DepartmentRequest;
import com.nandita.ems.dto.department.DepartmentResponse;

import java.util.List;

public interface DepartmentService {

    DepartmentResponse create(DepartmentRequest request);

    DepartmentResponse update(Long id, DepartmentRequest request);

    DepartmentResponse getById(Long id);

    List<DepartmentResponse> getAll();

    void delete(Long id);
}