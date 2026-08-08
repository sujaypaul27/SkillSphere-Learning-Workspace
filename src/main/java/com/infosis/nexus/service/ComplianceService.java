package com.infosis.nexus.service;

import com.infosis.nexus.dto.request.ComplianceRequest;
import com.infosis.nexus.dto.request.ComplianceUpdateRequest;
import com.infosis.nexus.dto.response.ComplianceResponse;
import com.infosis.nexus.entity.ComplianceStatus;

import java.util.List;

public interface ComplianceService {

    ComplianceResponse createCompliance(ComplianceRequest request);

    List<ComplianceResponse> getAllComplianceRecords();

    ComplianceResponse getComplianceById(Long id);

    ComplianceResponse updateCompliance(Long id, ComplianceUpdateRequest request);

    void deleteCompliance(Long id);

    List<ComplianceResponse> getComplianceByEmployeeId(Long employeeId);

    List<ComplianceResponse> getComplianceByCertificationId(Long certificationId);

    List<ComplianceResponse> getComplianceByStatus(ComplianceStatus status);
}