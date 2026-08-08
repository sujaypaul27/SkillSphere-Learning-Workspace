package com.infosis.nexus.service;

import com.infosis.nexus.dto.request.ExpiryTrackingRequest;
import com.infosis.nexus.dto.request.ExpiryTrackingUpdateRequest;
import com.infosis.nexus.dto.response.ExpiryTrackingResponse;

import java.util.List;

public interface ExpiryTrackingService {

    ExpiryTrackingResponse createExpiryTracking(
            ExpiryTrackingRequest request);

    List<ExpiryTrackingResponse> getAllExpiryRecords();

    ExpiryTrackingResponse getExpiryRecordById(Long id);

    ExpiryTrackingResponse updateExpiryTracking(
            Long id,
            ExpiryTrackingUpdateRequest request);

    void deleteExpiryTracking(Long id);

    List<ExpiryTrackingResponse> getByEmployeeId(Long employeeId);

    List<ExpiryTrackingResponse> getByCertificationId(Long certificationId);

    List<ExpiryTrackingResponse> getExpiredCertifications();

    List<ExpiryTrackingResponse> getExpiringWithin30Days();
}