package com.infosis.nexus.assessment;

import com.infosis.nexus.Employee.Employee;
import com.infosis.nexus.Employee.EmployeeRepository;
import com.infosis.nexus.Employee.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class Milestone1Tests {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private AssessmentRepository assessmentRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private AssessmentService assessmentService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        assessmentService = new AssessmentService(assessmentRepository, employeeService);
        employee = new Employee(1L, "John Smith", "Developer", "Engineering", 5, 4.2);
    }

    @Test
    void testCreateAssessment_Passed() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Assessment assessment = new Assessment(100L, 1L, 2L, 87.0, true);
        when(assessmentRepository.save(any(Assessment.class))).thenReturn(assessment);

        Assessment result = assessmentService.createAssessment(1L, 2L, 87.0);

        assertNotNull(result);
        assertTrue(result.getPassed());
        assertEquals(87.0, result.getScore());
        assertEquals(1L, result.getEmployeeId());
        assertEquals(2L, result.getSkillId());
        verify(assessmentRepository, times(1)).save(any(Assessment.class));
    }

    @Test
    void testCreateAssessment_Failed() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Assessment assessment = new Assessment(101L, 1L, 2L, 65.0, false);
        when(assessmentRepository.save(any(Assessment.class))).thenReturn(assessment);

        Assessment result = assessmentService.createAssessment(1L, 2L, 65.0);

        assertNotNull(result);
        assertFalse(result.getPassed());
        assertEquals(65.0, result.getScore());
        verify(assessmentRepository, times(1)).save(any(Assessment.class));
    }
}
