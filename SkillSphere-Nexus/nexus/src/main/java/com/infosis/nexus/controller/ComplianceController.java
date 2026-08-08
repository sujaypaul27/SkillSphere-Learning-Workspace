package com.infosis.nexus.controller;

import com.infosis.nexus.dto.request.ComplianceRequest;
import com.infosis.nexus.dto.request.ComplianceUpdateRequest;
import com.infosis.nexus.dto.response.ComplianceResponse;
import com.infosis.nexus.entity.ComplianceStatus;
import com.infosis.nexus.service.ComplianceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compliance")
public class ComplianceController {

    private final ComplianceService complianceService;

    public ComplianceController(ComplianceService complianceService) {
        this.complianceService = complianceService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComplianceResponse createCompliance(
            @Valid @RequestBody ComplianceRequest request) {

        return complianceService.createCompliance(request);
    }

    @GetMapping
    public List<ComplianceResponse> getAllComplianceRecords() {

        return complianceService.getAllComplianceRecords();
    }

    @GetMapping("/{id}")
    public ComplianceResponse getComplianceById(
            @PathVariable Long id) {

        return complianceService.getComplianceById(id);
    }

    @PutMapping("/{id}")
    public ComplianceResponse updateCompliance(
            @PathVariable Long id,
            @Valid @RequestBody ComplianceUpdateRequest request) {

        return complianceService.updateCompliance(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompliance(
            @PathVariable Long id) {

        complianceService.deleteCompliance(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ComplianceResponse> getComplianceByEmployeeId(
            @PathVariable Long employeeId) {

        return complianceService.getComplianceByEmployeeId(employeeId);
    }

    @GetMapping("/certification/{certificationId}")
    public List<ComplianceResponse> getComplianceByCertificationId(
            @PathVariable Long certificationId) {

        return complianceService.getComplianceByCertificationId(certificationId);
    }

    @GetMapping("/status/{status}")
    public List<ComplianceResponse> getComplianceByStatus(
            @PathVariable ComplianceStatus status) {

        return complianceService.getComplianceByStatus(status);
    }
}