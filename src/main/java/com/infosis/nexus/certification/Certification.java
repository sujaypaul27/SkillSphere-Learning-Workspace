package com.infosis.nexus.certification;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "certifications")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Certification name is required")
    private String certificationName;

    @NotBlank(message = "Provider is required")
    private String provider;

    private LocalDate issueDate;

    private LocalDate expiryDate;

    private String status;

    public Certification() {
    }

    public Certification(Long id, String certificationName, String provider,
                         LocalDate issueDate, LocalDate expiryDate, String status) {
        this.id = id;
        this.certificationName = certificationName;
        this.provider = provider;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCertificationName() {
        return certificationName;
    }

    public void setCertificationName(String certificationName) {
        this.certificationName = certificationName;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}