package com.nandita.ems.dto.document;

import com.nandita.ems.entity.enums.DocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentRequest {

    @NotNull
    private Long employeeId;

    @NotNull
    private DocumentType documentType;

    @NotBlank
    private String fileName;

    @NotBlank
    private String fileUrl;
}