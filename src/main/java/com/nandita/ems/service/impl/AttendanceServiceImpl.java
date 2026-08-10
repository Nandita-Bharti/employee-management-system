package com.nandita.ems.service.impl;

import com.nandita.ems.dto.attendance.AttendanceRequest;
import com.nandita.ems.dto.attendance.AttendanceResponse;
import com.nandita.ems.entity.Attendance;
import com.nandita.ems.entity.Employee;
import com.nandita.ems.mapper.AttendanceMapper;
import com.nandita.ems.repository.AttendanceRepository;
import com.nandita.ems.repository.EmployeeRepository;
import com.nandita.ems.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;
    private final AttendanceMapper attendanceMapper;

    @Override
    public AttendanceResponse create(AttendanceRequest request) {

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Attendance attendance = attendanceMapper.toEntity(request);
        attendance.setEmployee(employee);

        Attendance saved = attendanceRepository.save(attendance);

        return attendanceMapper.toResponse(saved);
    }

    @Override
    public AttendanceResponse update(Long id, AttendanceRequest request) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        attendance.setEmployee(employee);
        attendance.setAttendanceDate(request.getAttendanceDate());
        attendance.setCheckIn(request.getCheckIn());
        attendance.setCheckOut(request.getCheckOut());
        attendance.setStatus(request.getStatus());

        Attendance updated = attendanceRepository.save(attendance);

        return attendanceMapper.toResponse(updated);
    }

    @Override
    public AttendanceResponse getById(Long id) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        return attendanceMapper.toResponse(attendance);
    }

    @Override
    public List<AttendanceResponse> getAll() {

        return attendanceRepository.findAll()
                .stream()
                .map(attendanceMapper::toResponse)
                .toList();
    }

    @Override
    public List<AttendanceResponse> getByEmployee(Long employeeId) {

        if (!employeeRepository.existsById(employeeId)) {
            throw new RuntimeException("Employee not found");
        }

        return attendanceRepository.findByEmployeeId(employeeId)
                .stream()
                .map(attendanceMapper::toResponse)
                .toList();
    }

    @Override
    public List<AttendanceResponse> getByDate(LocalDate date) {

        return attendanceRepository.findByAttendanceDate(date)
                .stream()
                .map(attendanceMapper::toResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        attendanceRepository.delete(attendance);
    }
}