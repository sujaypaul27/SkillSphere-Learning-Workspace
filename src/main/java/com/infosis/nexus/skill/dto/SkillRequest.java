package com.infosis.nexus.skill.dto;

import com.infosis.nexus.skill.SkillCategory;
import jakarta.validation.constraints.*;

public class SkillRequest {
    @NotNull(message = " Employee ID is required")
    private Long employeeId;
    @NotBlank(message = "Skill name cannot be blank ")
    @Size( max = 100 , message = " Skill name cannot exceed 100 characters")
    private String skillName;
    @NotNull(message = "Category is required")
    private SkillCategory category;
    @NotNull ( message = "Proficiency level is required")
    @Min(value = 1 , message = " Proficiency level is at least 1 ")
    @Max(value = 10 , message = " Proficiency level is atmost 10 ")
    private Integer proficiencyLevel;
    @NotNull(message = " Years of experience is required ")
    @DecimalMin(value = "0.0" , message = "Experience cannot be negative")
    private Double yearsOfExperience;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public SkillCategory getCategory() {
        return category;
    }

    public void setCategory(SkillCategory category) {
        this.category = category;
    }

    public Integer getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setProficiencyLevel(Integer proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }

    public Double getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Double yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
