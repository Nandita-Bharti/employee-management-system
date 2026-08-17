package com.nandita.ems.service.impl;

import com.nandita.ems.dto.leave.LeaveRequestDto;
import com.nandita.ems.dto.leave.LeaveResponse;
import com.nandita.ems.entity.Employee;
import com.nandita.ems.entity.LeaveRequest;
import com.nandita.ems.entity.enums.LeaveStatus;
import com.nandita.ems.exception.ResourceNotFoundException;
import com.nandita.ems.mapper.LeaveRequestMapper;
import com.nandita.ems.repository.EmployeeRepository;
import com.nandita.ems.repository.LeaveRequestRepository;
import com.nandita.ems.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveRequestMapper leaveRequestMapper;

    @Override
    public LeaveResponse create(LeaveRequestDto request) {

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        LeaveRequest leaveRequest = leaveRequestMapper.toEntity(request);

        leaveRequest.setEmployee(employee);
        leaveRequest.setStatus(LeaveStatus.PENDING);

        LeaveRequest saved = leaveRequestRepository.save(leaveRequest);

        return leaveRequestMapper.toResponse(saved);
    }

    @Override
    public LeaveResponse update(Long id, LeaveRequestDto request) {

        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        leaveRequest.setEmployee(employee);
        leaveRequest.setLeaveType(request.getLeaveType());
        leaveRequest.setStartDate(request.getStartDate());
        leaveRequest.setEndDate(request.getEndDate());
        leaveRequest.setReason(request.getReason());

        LeaveRequest updated = leaveRequestRepository.save(leaveRequest);

        return leaveRequestMapper.toResponse(updated);
    }

    @Override
    public LeaveResponse getById(Long id) {

        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));

        return leaveRequestMapper.toResponse(leaveRequest);
    }

    @Override
    public List<LeaveResponse> getAll() {

        return leaveRequestRepository.findAll()
                .stream()
                .map(leaveRequestMapper::toResponse)
                .toList();
    }

    @Override
    public List<LeaveResponse> getByEmployee(Long employeeId) {

        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found");
        }

        return leaveRequestRepository.findByEmployeeId(employeeId)
                .stream()
                .map(leaveRequestMapper::toResponse)
                .toList();
    }

    @Override
    public LeaveResponse approve(Long id) {

        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));

        leaveRequest.setStatus(LeaveStatus.APPROVED);

        return leaveRequestMapper.toResponse(
                leaveRequestRepository.save(leaveRequest)
        );
    }

    @Override
    public LeaveResponse reject(Long id) {

        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));

        leaveRequest.setStatus(LeaveStatus.REJECTED);

        return leaveRequestMapper.toResponse(
                leaveRequestRepository.save(leaveRequest)
        );
    }

    @Override
    public void delete(Long id) {

        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));

        leaveRequestRepository.delete(leaveRequest);
    }
}