package com.nandita.ems.service;

import com.nandita.ems.dto.attendance.AttendanceRequest;
import com.nandita.ems.dto.attendance.AttendanceResponse;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    AttendanceResponse create(AttendanceRequest request);

    AttendanceResponse update(Long id, AttendanceRequest request);

    AttendanceResponse getById(Long id);

    List<AttendanceResponse> getAll();

    List<AttendanceResponse> getByEmployee(Long employeeId);

    List<AttendanceResponse> getByDate(LocalDate date);

    void delete(Long id);
}