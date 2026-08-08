package com.infosis.nexus.dto.request;

import com.infosis.nexus.entity.ComplianceStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplianceUpdateRequest {

    @NotNull(message = "Compliance Status is required")
    private ComplianceStatus status;

    @NotNull(message = "Compliance Date is required")
    private LocalDate complianceDate;

    private String remarks;
}