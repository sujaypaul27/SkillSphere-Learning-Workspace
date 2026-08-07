package com.infosis.nexus.certification;

import com.infosis.nexus.audit.AuditLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificationService {

    private final CertificationRepository repository;
    private final AuditLogService auditLogService;

    public CertificationService(CertificationRepository repository, AuditLogService auditLogService) {
        this.repository = repository;
        this.auditLogService = auditLogService;
    }

    public List<Certification> getAll() {
        return repository.findAll();
    }

    public Certification getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certification not found with id: " + id));
    }

    public Certification create(Certification certification) {
        Certification saved = repository.save(certification);
        auditLogService.log("CREATE", "Certification", saved.getId(),
                "Created certification: " + saved.getCertificationName() + " (Provider: " + saved.getProvider() + ") for Employee ID: " + saved.getEmployeeid());
        return saved;
    }

    public Certification update(Long id, Certification certification) {
        Certification existing = getById(id);

        existing.setCertificationName(certification.getCertificationName());
        existing.setProvider(certification.getProvider());
        existing.setIssueDate(certification.getIssueDate());
        existing.setExpiryDate(certification.getExpiryDate());
        existing.setStatus(certification.getStatus());

        Certification updated = repository.save(existing);
        auditLogService.log("UPDATE", "Certification", updated.getId(),
                "Updated certification: " + updated.getCertificationName() + " status to: " + updated.getStatus());
        return updated;
    }

    public void delete(Long id) {
        Certification existing = getById(id);
        repository.delete(existing);
        auditLogService.log("DELETE", "Certification", id,
                "Deleted certification: " + existing.getCertificationName() + " (Provider: " + existing.getProvider() + ") for Employee ID: " + existing.getEmployeeid());
    }
}