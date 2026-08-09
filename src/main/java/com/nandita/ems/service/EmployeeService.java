package com.nandita.ems.service;

import com.nandita.ems.dto.employee.EmployeeRequest;
import com.nandita.ems.dto.employee.EmployeeResponse;
import org.springframework.data.domain.Page;

public interface EmployeeService {

    EmployeeResponse create(EmployeeRequest request);

    EmployeeResponse update(Long id, EmployeeRequest request);

    EmployeeResponse getById(Long id);

    Page<EmployeeResponse> getAll(int page, int size, String sortBy);

    Page<EmployeeResponse> search(String keyword, int page, int size);

    void delete(Long id);


}