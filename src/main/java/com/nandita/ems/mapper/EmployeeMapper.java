package com.nandita.ems.mapper;

import com.nandita.ems.dto.employee.EmployeeRequest;
import com.nandita.ems.dto.employee.EmployeeResponse;
import com.nandita.ems.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true)
)
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Employee toEntity(EmployeeRequest request);

    @Mapping(source = "department.name", target = "department")
    @Mapping(source = "user.username", target = "username")
    EmployeeResponse toResponse(Employee employee);
}