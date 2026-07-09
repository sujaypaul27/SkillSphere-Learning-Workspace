package com.infosis.nexus.skill;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {
    private final AssessmentService service;

    public AssessmentController(AssessmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Assessment> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Assessment getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Assessment> getAssessmentsByEmployeeId(@PathVariable Long employeeId) {
        return service.getAssessmentsByEmployeeId(employeeId);
    }

    @PostMapping
    public Assessment createAssessment(@Valid @RequestBody AssessmentRequest request) {
        return service.createAssessment(request.getEmployeeId(), request.getSkillId(), request.getScore());
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Assessment deleted successfully";
    }

    public static class AssessmentRequest {
        @NotNull(message = "Employee ID is required")
        private Long employeeId;

        @NotNull(message = "Skill ID is required")
        private Long skillId;

        @NotNull(message = "Score is required")
        private Double score;

        public AssessmentRequest() {
        }

        public Long getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(Long employeeId) {
            this.employeeId = employeeId;
        }

        public Long getSkillId() {
            return skillId;
        }

        public void setSkillId(Long skillId) {
            this.skillId = skillId;
        }

        public Double getScore() {
            return score;
        }

        public void setScore(Double score) {
            this.score = score;
        }
    }
}
