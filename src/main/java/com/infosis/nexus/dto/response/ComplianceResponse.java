package com.infosis.nexus.dto.response;

import com.infosis.nexus.entity.ComplianceStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplianceResponse {

    private Long id;

    private Long employeeId;

    private Long certificationId;

    private ComplianceStatus status;

    private LocalDate complianceDate;

    private LocalDate expiryDate;

    private String remarks;
}