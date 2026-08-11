package com.nandita.ems.mapper;

import com.nandita.ems.dto.leave.LeaveRequestDto;
import com.nandita.ems.dto.leave.LeaveResponse;
import com.nandita.ems.entity.LeaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true)
)
public interface LeaveRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    LeaveRequest toEntity(LeaveRequestDto request);

    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(
            expression = "java(leaveRequest.getEmployee().getFirstName() + \" \" + leaveRequest.getEmployee().getLastName())",
            target = "employeeName"
    )
    LeaveResponse toResponse(LeaveRequest leaveRequest);
}