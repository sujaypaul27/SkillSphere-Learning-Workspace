package com.infosis.nexus.skill.mapper;

import com.infosis.nexus.skill.Skill;
import com.infosis.nexus.skill.dto.SkillRequest;
import com.infosis.nexus.skill.dto.SkillResponse;
import org.springframework.stereotype.Component;

@Component
public class SkillMapper {

    public Skill toEntity(SkillRequest request) {
        return new Skill(
                request.getEmployeeId(),
                request.getSkillName(),
                request.getCategory(),
                request.getProficiencyLevel(),
                request.getYearsOfExperience()
        );
    }

    public SkillResponse toResponse(Skill skill) {
        return new SkillResponse(
                skill.getId(),
                skill.getEmployeeId(),
                skill.getSkillName(),
                skill.getCategory(),
                skill.getProficiencyLevel(),
                skill.getYearsOfExperience(),
                skill.getIsVerified(),
                skill.getCreatedAt(),
                skill.getUpdatedAt()
        );
    }
}