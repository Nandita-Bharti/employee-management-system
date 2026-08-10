package com.nandita.ems.dto.attendance;

import com.nandita.ems.entity.enums.AttendanceStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceRequest {

    @NotNull
    private Long employeeId;

    @NotNull
    private LocalDate attendanceDate;

    @NotNull
    private LocalTime checkIn;

    private LocalTime checkOut;

    @NotNull
    private AttendanceStatus status;
}