package com.nandita.ems.dto.leave;

import com.nandita.ems.entity.enums.LeaveStatus;
import com.nandita.ems.entity.enums.LeaveType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveResponse {

    private Long id;

    private Long employeeId;

    private String employeeName;

    private LeaveType leaveType;

    private LeaveStatus status;

    private LocalDate startDate;

    private LocalDate endDate;

    private String reason;
}