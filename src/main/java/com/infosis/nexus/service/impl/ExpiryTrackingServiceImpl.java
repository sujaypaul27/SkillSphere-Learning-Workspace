package com.infosis.nexus.service.impl;

import com.infosis.nexus.dto.request.ExpiryTrackingRequest;
import com.infosis.nexus.dto.request.ExpiryTrackingUpdateRequest;
import com.infosis.nexus.dto.response.ExpiryTrackingResponse;
import com.infosis.nexus.entity.ExpiryTracking;
import com.infosis.nexus.mapper.ExpiryTrackingMapper;
import com.infosis.nexus.repository.ExpiryTrackingRepository;
import com.infosis.nexus.service.ExpiryTrackingService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpiryTrackingServiceImpl implements ExpiryTrackingService {

    private final ExpiryTrackingRepository expiryTrackingRepository;

    public ExpiryTrackingServiceImpl(
            ExpiryTrackingRepository expiryTrackingRepository) {
        this.expiryTrackingRepository = expiryTrackingRepository;
    }

    @Override
    public ExpiryTrackingResponse createExpiryTracking(
            ExpiryTrackingRequest request) {

        ExpiryTracking expiryTracking =
                ExpiryTrackingMapper.toEntity(request);

        updateExpiryStatus(expiryTracking);

        ExpiryTracking savedExpiryTracking =
                expiryTrackingRepository.save(expiryTracking);

        return ExpiryTrackingMapper.toResponse(savedExpiryTracking);
    }

    @Override
    public List<ExpiryTrackingResponse> getAllExpiryRecords() {

        return expiryTrackingRepository.findAll()
                .stream()
                .map(ExpiryTrackingMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ExpiryTrackingResponse getExpiryRecordById(Long id) {

        ExpiryTracking expiryTracking =
                expiryTrackingRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Expiry tracking record not found"));

        updateExpiryStatus(expiryTracking);

        return ExpiryTrackingMapper.toResponse(expiryTracking);
    }

    @Override
    public ExpiryTrackingResponse updateExpiryTracking(
            Long id,
            ExpiryTrackingUpdateRequest request) {

        ExpiryTracking expiryTracking =
                expiryTrackingRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Expiry tracking record not found"));

        expiryTracking.setExpiryDate(request.getExpiryDate());

        updateExpiryStatus(expiryTracking);

        ExpiryTracking updatedExpiryTracking =
                expiryTrackingRepository.save(expiryTracking);

        return ExpiryTrackingMapper.toResponse(updatedExpiryTracking);
    }

    @Override
    public void deleteExpiryTracking(Long id) {

        ExpiryTracking expiryTracking =
                expiryTrackingRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Expiry tracking record not found"));

        expiryTrackingRepository.delete(expiryTracking);
    }

    @Override
    public List<ExpiryTrackingResponse> getByEmployeeId(
            Long employeeId) {

        return expiryTrackingRepository
                .findByEmployeeId(employeeId)
                .stream()
                .map(ExpiryTrackingMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExpiryTrackingResponse> getByCertificationId(
            Long certificationId) {

        return expiryTrackingRepository
                .findByCertificationId(certificationId)
                .stream()
                .map(ExpiryTrackingMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExpiryTrackingResponse> getExpiredCertifications() {

        return expiryTrackingRepository
                .findByExpiryDateBefore(LocalDate.now())
                .stream()
                .map(ExpiryTrackingMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExpiryTrackingResponse> getExpiringWithin30Days() {

        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plusDays(30);

        return expiryTrackingRepository
                .findByExpiryDateBetween(today, thirtyDaysLater)
                .stream()
                .map(ExpiryTrackingMapper::toResponse)
                .collect(Collectors.toList());
    }

    private void updateExpiryStatus(
            ExpiryTracking expiryTracking) {

        LocalDate today = LocalDate.now();
        LocalDate expiryDate = expiryTracking.getExpiryDate();

        if (expiryDate == null) {
            expiryTracking.setExpiryStatus("UNKNOWN");
            expiryTracking.setLastCheckedDate(today);
            return;
        }

        if (expiryDate.isBefore(today)) {

            expiryTracking.setExpiryStatus("EXPIRED");

        } else if (!expiryDate.isAfter(today.plusDays(30))) {

            expiryTracking.setExpiryStatus("EXPIRING_SOON");

        } else {

            expiryTracking.setExpiryStatus("VALID");
        }

        expiryTracking.setLastCheckedDate(today);
    }
}