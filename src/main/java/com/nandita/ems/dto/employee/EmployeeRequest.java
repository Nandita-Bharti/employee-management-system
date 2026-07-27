package com.nandita.ems.dto.employee;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Pattern(regexp = "^[6-9]\\d{9}$")
    private String phone;

    @NotBlank
    private String address;

    @NotNull
    private BigDecimal salary;

    @NotNull
    private LocalDate joiningDate;

    @NotNull
    private Long departmentId;
}