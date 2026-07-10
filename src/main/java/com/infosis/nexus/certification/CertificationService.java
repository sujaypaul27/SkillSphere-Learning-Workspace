package com.infosis.nexus.certification;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificationService {

    private final CertificationRepository repository;

    public CertificationService(CertificationRepository repository) {
        this.repository = repository;
    }

    public List<Certification> getAll() {
        return repository.findAll();
    }

    public Certification getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certification not found with id: " + id));
    }

    public Certification create(Certification certification) {
        return repository.save(certification);
    }

    public Certification update(Long id, Certification certification) {
        Certification existing = getById(id);

        existing.setCertificationName(certification.getCertificationName());
        existing.setProvider(certification.getProvider());
        existing.setIssueDate(certification.getIssueDate());
        existing.setExpiryDate(certification.getExpiryDate());
        existing.setStatus(certification.getStatus());

        return repository.save(existing);
    }

    public void delete(Long id) {
        Certification existing = getById(id);
        repository.delete(existing);
    }
}