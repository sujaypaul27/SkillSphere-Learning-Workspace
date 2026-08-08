package com.infosis.nexus.repository;

import com.infosis.nexus.entity.ExpiryTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpiryTrackingRepository extends JpaRepository<ExpiryTracking, Long> {

    List<ExpiryTracking> findByEmployeeId(Long employeeId);

    List<ExpiryTracking> findByCertificationId(Long certificationId);

    List<ExpiryTracking> findByExpiryDateBefore(LocalDate date);

    List<ExpiryTracking> findByExpiryDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );

    List<ExpiryTracking> findByExpiryStatus(String expiryStatus);
}