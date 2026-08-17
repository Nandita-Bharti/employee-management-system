package com.nandita.ems.service.impl;

import com.nandita.ems.dto.department.DepartmentRequest;
import com.nandita.ems.dto.department.DepartmentResponse;
import com.nandita.ems.entity.Department;
import com.nandita.ems.exception.ResourceNotFoundException;
import com.nandita.ems.mapper.DepartmentMapper;
import com.nandita.ems.repository.DepartmentRepository;
import com.nandita.ems.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public DepartmentResponse create(DepartmentRequest request) {

        if (departmentRepository.existsByName(request.getName())) {
            throw new ResourceNotFoundException("Department already exists");
        }

        Department department = departmentMapper.toEntity(request);

        Department saved = departmentRepository.save(department);

        return departmentMapper.toResponse(saved);
    }

    @Override
    public DepartmentResponse update(Long id, DepartmentRequest request) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        if (!department.getName().equals(request.getName())
                && departmentRepository.existsByName(request.getName())) {
            throw new ResourceNotFoundException("Department already exists");
        }

        department.setName(request.getName());
        department.setDescription(request.getDescription());

        Department updated = departmentRepository.save(department);

        return departmentMapper.toResponse(updated);
    }

    @Override
    public DepartmentResponse getById(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        return departmentMapper.toResponse(department);
    }

    @Override
    public List<DepartmentResponse> getAll() {

        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        departmentRepository.delete(department);
    }
}