package com.infosis.nexus.audit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuditLogTests {

    @Mock
    private AuditLogRepository repository;

    private AuditLogService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new AuditLogService(repository);
    }

    @Test
    void testLogAction() {
        AuditLog mockLog = new AuditLog("CREATE", "Certification", 1L, "Created cert");
        when(repository.save(any(AuditLog.class))).thenReturn(mockLog);

        AuditLog saved = service.log("CREATE", "Certification", 1L, "Created cert");

        assertNotNull(saved);
        assertEquals("CREATE", saved.getAction());
        assertEquals("Certification", saved.getEntityName());
        assertEquals(1L, saved.getEntityId());
        assertEquals("Created cert", saved.getDetails());
    }

    @Test
    void testGetByEntity() {
        AuditLog log1 = new AuditLog("CREATE", "Certification", 1L, "Created cert");
        AuditLog log2 = new AuditLog("UPDATE", "Certification", 1L, "Updated cert");

        when(repository.findByEntityName("Certification")).thenReturn(Arrays.asList(log1, log2));

        List<AuditLog> results = service.getByEntity("Certification");

        assertNotNull(results);
        assertEquals(2, results.size());
        assertEquals("CREATE", results.get(0).getAction());
        assertEquals("UPDATE", results.get(1).getAction());
    }

    @Test
    void testGetByEntityAndId() {
        AuditLog log1 = new AuditLog("CREATE", "Certification", 5L, "Created cert 5");

        when(repository.findByEntityNameAndEntityId("Certification", 5L)).thenReturn(List.of(log1));

        List<AuditLog> results = service.getByEntityAndId("Certification", 5L);

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(5L, results.get(0).getEntityId());
    }
}
