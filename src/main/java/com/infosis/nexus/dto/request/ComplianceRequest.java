package com.infosis.nexus.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplianceRequest {

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotNull(message = "Certification ID is required")
    private Long certificationId;

    @NotNull(message = "Expiry Date is required")
    private LocalDate expiryDate;

    @NotBlank(message = "Remarks are required")
    private String remarks;
}