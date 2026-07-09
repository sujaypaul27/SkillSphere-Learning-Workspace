package com.infosis.nexus.skill;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentService {
    private final AssessmentRepository repository;
    private final EmployeeService employeeService;

    // Passing threshold for assessments
    private static final double PASS_THRESHOLD = 75.0;

    public AssessmentService(AssessmentRepository repository, EmployeeService employeeService) {
        this.repository = repository;
        this.employeeService = employeeService;
    }

    public List<Assessment> getAll() {
        return repository.findAll();
    }

    public Assessment getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assessment not found with id: " + id));
    }

    public List<Assessment> getAssessmentsByEmployeeId(Long employeeId) {
        // Verify employee exists
        employeeService.getById(employeeId);
        return repository.findByEmployeeId(employeeId);
    }

    public Assessment createAssessment(Long employeeId, Long skillId, Double score) {
        // Verify employee exists
        employeeService.getById(employeeId);

        boolean passed = score >= PASS_THRESHOLD;

        Assessment assessment = new Assessment(null, employeeId, skillId, score, passed);
        return repository.save(assessment);
    }

    public void delete(Long id) {
        Assessment existing = getById(id);
        repository.delete(existing);
    }
}
