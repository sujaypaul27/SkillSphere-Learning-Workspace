package com.infosis.nexus.skill.dto;

import com.infosis.nexus.skill.SkillCategory;

import java.time.LocalDateTime;

public class SkillResponse {
    private Long id ;
    private Long employeeId;
     private String skillName;
     private SkillCategory category;
     private Integer proficiencyLevel;
     private Double  yearsOfExperience;
     private Boolean isVerified ;
     private LocalDateTime createdAt;
     private  LocalDateTime updatedAt;

    public SkillResponse(Long id, Long employeeId, String skillName, SkillCategory category, Integer proficiencyLevel, Double yearsOfExperience, Boolean isVerified, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.employeeId = employeeId;
        this.skillName = skillName;
        this.category = category;
        this.proficiencyLevel = proficiencyLevel;
        this.yearsOfExperience = yearsOfExperience;
        this.isVerified = isVerified;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getSkillName() {
        return skillName;
    }

    public SkillCategory getCategory() {
        return category;
    }

    public Integer getProficiencyLevel() {
        return proficiencyLevel;
    }

    public Double getYearsOfExperience() {
        return yearsOfExperience;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
