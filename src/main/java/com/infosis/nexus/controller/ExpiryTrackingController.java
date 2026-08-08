package com.infosis.nexus.controller;

import com.infosis.nexus.dto.request.ExpiryTrackingRequest;
import com.infosis.nexus.dto.request.ExpiryTrackingUpdateRequest;
import com.infosis.nexus.dto.response.ExpiryTrackingResponse;
import com.infosis.nexus.service.ExpiryTrackingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expiry-tracking")
public class ExpiryTrackingController {

    private final ExpiryTrackingService expiryTrackingService;

    public ExpiryTrackingController(
            ExpiryTrackingService expiryTrackingService) {
        this.expiryTrackingService = expiryTrackingService;
    }

    @PostMapping
    public ResponseEntity<ExpiryTrackingResponse> createExpiryTracking(
            @Valid @RequestBody ExpiryTrackingRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(expiryTrackingService.createExpiryTracking(request));
    }

    @GetMapping
    public ResponseEntity<List<ExpiryTrackingResponse>> getAllExpiryRecords() {

        return ResponseEntity.ok(
                expiryTrackingService.getAllExpiryRecords()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpiryTrackingResponse> getExpiryRecordById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                expiryTrackingService.getExpiryRecordById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpiryTrackingResponse> updateExpiryTracking(
            @PathVariable Long id,
            @Valid @RequestBody ExpiryTrackingUpdateRequest request) {

        return ResponseEntity.ok(
                expiryTrackingService.updateExpiryTracking(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpiryTracking(
            @PathVariable Long id) {

        expiryTrackingService.deleteExpiryTracking(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<ExpiryTrackingResponse>> getByEmployeeId(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                expiryTrackingService.getByEmployeeId(employeeId)
        );
    }

    @GetMapping("/certification/{certificationId}")
    public ResponseEntity<List<ExpiryTrackingResponse>> getByCertificationId(
            @PathVariable Long certificationId) {

        return ResponseEntity.ok(
                expiryTrackingService.getByCertificationId(certificationId)
        );
    }

    @GetMapping("/expired")
    public ResponseEntity<List<ExpiryTrackingResponse>> getExpiredCertifications() {

        return ResponseEntity.ok(
                expiryTrackingService.getExpiredCertifications()
        );
    }

    @GetMapping("/expiring-soon")
    public ResponseEntity<List<ExpiryTrackingResponse>> getExpiringWithin30Days() {

        return ResponseEntity.ok(
                expiryTrackingService.getExpiringWithin30Days()
        );
    }
}