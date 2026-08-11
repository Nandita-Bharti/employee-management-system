package com.nandita.ems.mapper;

import com.nandita.ems.dto.document.DocumentRequest;
import com.nandita.ems.dto.document.DocumentResponse;
import com.nandita.ems.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true)
)
public interface DocumentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Document toEntity(DocumentRequest request);

    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(
            expression = "java(document.getEmployee().getFirstName() + \" \" + document.getEmployee().getLastName())",
            target = "employeeName"
    )
    DocumentResponse toResponse(Document document);
}