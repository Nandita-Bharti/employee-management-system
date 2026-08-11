package com.nandita.ems.dto.document;

import com.nandita.ems.entity.enums.DocumentType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentResponse {

    private Long id;

    private Long employeeId;

    private String employeeName;

    private DocumentType documentType;

    private String fileName;

    private String fileUrl;
}