package com.infosis.nexus.audit;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuditLogService {

    private final AuditLogRepository repository;

    public AuditLogService(AuditLogRepository repository) {
        this.repository = repository;
    }

    public List<AuditLog> getAll() {
        return repository.findAll();
    }

    public List<AuditLog> getByEntity(String entityName) {
        return repository.findByEntityName(entityName);
    }

    public List<AuditLog> getByEntityAndId(String entityName, Long entityId) {
        return repository.findByEntityNameAndEntityId(entityName, entityId);
    }

    public AuditLog log(String action, String entityName, Long entityId, String details) {
        AuditLog log = new AuditLog(action, entityName, entityId, details);
        return repository.save(log);
    }
}
