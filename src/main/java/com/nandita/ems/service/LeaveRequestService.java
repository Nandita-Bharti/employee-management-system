package com.nandita.ems.service;

import com.nandita.ems.dto.leave.LeaveRequestDto;
import com.nandita.ems.dto.leave.LeaveResponse;

import java.util.List;

public interface LeaveRequestService {

    LeaveResponse create(LeaveRequestDto request);

    LeaveResponse update(Long id, LeaveRequestDto request);

    LeaveResponse getById(Long id);

    List<LeaveResponse> getAll();

    List<LeaveResponse> getByEmployee(Long employeeId);

    LeaveResponse approve(Long id);

    LeaveResponse reject(Long id);

    void delete(Long id);
}