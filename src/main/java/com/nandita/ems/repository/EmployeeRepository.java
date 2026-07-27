package com.nandita.ems.repository;

import com.nandita.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByPhone(String phone);
}