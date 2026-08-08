package com.infosis.nexus.repository;

import com.infosis.nexus.entity.Compliance;
import com.infosis.nexus.entity.ComplianceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplianceRepository extends JpaRepository<Compliance, Long> {

    List<Compliance> findByEmployeeId(Long employeeId);

    List<Compliance> findByCertificationId(Long certificationId);

    List<Compliance> findByStatus(ComplianceStatus status);

    boolean existsByEmployeeIdAndCertificationId(
            Long employeeId,
            Long certificationId
    );
}