package com.infosis.nexus.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpiryTrackingRequest {

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotNull(message = "Certification ID is required")
    private Long certificationId;

    @NotNull(message = "Expiry Date is required")
    private LocalDate expiryDate;
}