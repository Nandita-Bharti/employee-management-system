package com.nandita.ems.dto.employee;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String phone;

    private String address;

    private BigDecimal salary;

    private LocalDate joiningDate;

    private String department;

    private String username;
}