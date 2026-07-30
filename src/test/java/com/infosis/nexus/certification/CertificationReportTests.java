package com.infosis.nexus.certification;

import com.infosis.nexus.Employee.Employee;
import com.infosis.nexus.Employee.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CertificationReportTests {

    @Mock
    private CertificationService certificationService;

    @Mock
    private EmployeeService employeeService;

    private CertificationReportController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new CertificationReportController(certificationService, employeeService);
    }

    @Test
    void testGetSummaryReport() {
        Certification cert1 = new Certification(1L, "AWS SAA", "AWS", 1L, LocalDate.now().minusMonths(6), LocalDate.now().plusMonths(6), "Active");
        Certification cert2 = new Certification(2L, "Java OCP", "Oracle", 1L, LocalDate.now().minusMonths(24), LocalDate.now().minusMonths(1), "Expired");
        Certification cert3 = new Certification(3L, "CKA", "CNCF", 2L, LocalDate.now().minusMonths(11), LocalDate.now().plusDays(15), "Active");

        when(certificationService.getAll()).thenReturn(Arrays.asList(cert1, cert2, cert3));

        CertificationReportController.CertificationSummaryReport summary = controller.getSummaryReport();

        assertNotNull(summary);
        assertEquals(2, summary.getTotalActive()); // cert1 and cert3 (not expired)
        assertEquals(1, summary.getTotalExpiringIn30Days()); // cert3 expires in 15 days
        assertEquals(66.6, summary.getRenewalRate(), 0.1); // 2/3 * 100
    }

    @Test
    void testGetExpiringCertifications() {
        Certification cert1 = new Certification(1L, "AWS SAA", "AWS", 1L, LocalDate.now().minusMonths(6), LocalDate.now().plusMonths(6), "Active");
        // Expiring in 15 days
        Certification cert2 = new Certification(2L, "CKA", "CNCF", 2L, LocalDate.now().minusMonths(11), LocalDate.now().plusDays(15), "Active");

        when(certificationService.getAll()).thenReturn(Arrays.asList(cert1, cert2));
        
        Employee emp = new Employee(2L, "Alice", "QA", "Testing", 2, 4.0);
        when(employeeService.getById(2L)).thenReturn(emp);

        List<CertificationReportController.ExpiringCertificationResponse> expiringList = controller.getExpiringCertifications();

        assertNotNull(expiringList);
        assertEquals(1, expiringList.size());
        assertEquals("CKA", expiringList.get(0).getCertificationName());
        assertEquals("Alice", expiringList.get(0).getEmployeeName());
        assertEquals(15, expiringList.get(0).getDaysRemaining());
    }
}
