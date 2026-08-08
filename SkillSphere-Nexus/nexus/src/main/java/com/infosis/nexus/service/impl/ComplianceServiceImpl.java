package com.infosis.nexus.service.impl;

import com.infosis.nexus.dto.request.ComplianceRequest;
import com.infosis.nexus.dto.request.ComplianceUpdateRequest;
import com.infosis.nexus.dto.response.ComplianceResponse;
import com.infosis.nexus.entity.Compliance;
import com.infosis.nexus.entity.ComplianceStatus;
import com.infosis.nexus.mapper.ComplianceMapper;
import com.infosis.nexus.repository.ComplianceRepository;
import com.infosis.nexus.service.ComplianceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplianceServiceImpl implements ComplianceService {

    private final ComplianceRepository complianceRepository;

    public ComplianceServiceImpl(ComplianceRepository complianceRepository) {
        this.complianceRepository = complianceRepository;
    }

    @Override
    public ComplianceResponse createCompliance(ComplianceRequest request) {

        if (complianceRepository.existsByEmployeeIdAndCertificationId(
                request.getEmployeeId(),
                request.getCertificationId())) {

            throw new RuntimeException("Compliance record already exists.");
        }

        Compliance compliance = ComplianceMapper.toEntity(request);
        Compliance savedCompliance = complianceRepository.save(compliance);

        return ComplianceMapper.toResponse(savedCompliance);
    }

    @Override
    public List<ComplianceResponse> getAllComplianceRecords() {

        return complianceRepository.findAll()
                .stream()
                .map(ComplianceMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ComplianceResponse getComplianceById(Long id) {

        Compliance compliance = complianceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compliance record not found"));

        return ComplianceMapper.toResponse(compliance);
    }

    @Override
    public ComplianceResponse updateCompliance(Long id, ComplianceUpdateRequest request) {

        Compliance compliance = complianceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compliance record not found"));

        compliance.setStatus(request.getStatus());
        compliance.setComplianceDate(request.getComplianceDate());
        compliance.setRemarks(request.getRemarks());

        Compliance updatedCompliance = complianceRepository.save(compliance);

        return ComplianceMapper.toResponse(updatedCompliance);
    }

    @Override
    public void deleteCompliance(Long id) {

        Compliance compliance = complianceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compliance record not found"));

        complianceRepository.delete(compliance);
    }

    @Override
    public List<ComplianceResponse> getComplianceByEmployeeId(Long employeeId) {

        return complianceRepository.findByEmployeeId(employeeId)
                .stream()
                .map(ComplianceMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ComplianceResponse> getComplianceByCertificationId(Long certificationId) {

        return complianceRepository.findByCertificationId(certificationId)
                .stream()
                .map(ComplianceMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ComplianceResponse> getComplianceByStatus(ComplianceStatus status) {

        return complianceRepository.findByStatus(status)
                .stream()
                .map(ComplianceMapper::toResponse)
                .collect(Collectors.toList());
    }
}