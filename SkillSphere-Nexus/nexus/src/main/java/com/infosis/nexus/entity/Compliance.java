package com.infosis.nexus.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "compliance_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compliance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    private Long certificationId;

    @Enumerated(EnumType.STRING)
    private ComplianceStatus status;

    private LocalDate complianceDate;

    private LocalDate expiryDate;

    private String remarks;
}