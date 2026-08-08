package com.infosis.nexus.mapper;

import com.infosis.nexus.dto.request.ComplianceRequest;
import com.infosis.nexus.dto.response.ComplianceResponse;
import com.infosis.nexus.entity.Compliance;
import com.infosis.nexus.entity.ComplianceStatus;

import java.time.LocalDate;

public class ComplianceMapper {

    public static Compliance toEntity(ComplianceRequest request) {

        return Compliance.builder()
                .employeeId(request.getEmployeeId())
                .certificationId(request.getCertificationId())
                .expiryDate(request.getExpiryDate())
                .remarks(request.getRemarks())
                .status(ComplianceStatus.PENDING)
                .complianceDate(LocalDate.now())
                .build();
    }

    public static ComplianceResponse toResponse(Compliance compliance) {

        return ComplianceResponse.builder()
                .id(compliance.getId())
                .employeeId(compliance.getEmployeeId())
                .certificationId(compliance.getCertificationId())
                .status(compliance.getStatus())
                .complianceDate(compliance.getComplianceDate())
                .expiryDate(compliance.getExpiryDate())
                .remarks(compliance.getRemarks())
                .build();
    }
}