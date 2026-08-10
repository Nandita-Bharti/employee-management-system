package com.nandita.ems.mapper;
import com.nandita.ems.dto.attendance.AttendanceRequest;
import com.nandita.ems.dto.attendance.AttendanceResponse;
import com.nandita.ems.entity.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true)
)
public interface AttendanceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Attendance toEntity(AttendanceRequest request);

    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(
            expression = "java(attendance.getEmployee().getFirstName() + \" \" + attendance.getEmployee().getLastName())",
            target = "employeeName"
    )
    AttendanceResponse toResponse(Attendance attendance);
}