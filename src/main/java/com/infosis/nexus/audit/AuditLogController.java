package com.infosis.nexus.audit;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
@CrossOrigin(origins = "*")
public class AuditLogController {

    private final AuditLogService service;

    public AuditLogController(AuditLogService service) {
        this.service = service;
    }

    @GetMapping
    public List<AuditLog> getAll() {
        return service.getAll();
    }

    @GetMapping("/entity/{entityName}")
    public List<AuditLog> getByEntity(@PathVariable String entityName) {
        return service.getByEntity(entityName);
    }

    @GetMapping("/entity/{entityName}/{entityId}")
    public List<AuditLog> getByEntityAndId(@PathVariable String entityName, @PathVariable Long entityId) {
        return service.getByEntityAndId(entityName, entityId);
    }
}
