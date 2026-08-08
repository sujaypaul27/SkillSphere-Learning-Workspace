package com.infosis.nexus.mapper;

import com.infosis.nexus.dto.request.ExpiryTrackingRequest;
import com.infosis.nexus.dto.response.ExpiryTrackingResponse;
import com.infosis.nexus.entity.ExpiryTracking;

import java.time.LocalDate;

public class ExpiryTrackingMapper {

    public static ExpiryTracking toEntity(ExpiryTrackingRequest request) {

        return ExpiryTracking.builder()
                .employeeId(request.getEmployeeId())
                .certificationId(request.getCertificationId())
                .expiryDate(request.getExpiryDate())
                .lastCheckedDate(LocalDate.now())
                .build();
    }

    public static ExpiryTrackingResponse toResponse(
            ExpiryTracking expiryTracking) {

        return ExpiryTrackingResponse.builder()
                .id(expiryTracking.getId())
                .employeeId(expiryTracking.getEmployeeId())
                .certificationId(expiryTracking.getCertificationId())
                .expiryDate(expiryTracking.getExpiryDate())
                .lastCheckedDate(expiryTracking.getLastCheckedDate())
                .expiryStatus(expiryTracking.getExpiryStatus())
                .build();
    }
}