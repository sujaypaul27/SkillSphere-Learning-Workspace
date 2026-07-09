package com.infosis.nexus.skill;

import com.infosis.nexus.skill.dto.SkillRequest;
import com.infosis.nexus.skill.dto.SkillResponse;

import java.util.List;

public interface SkillService {
    SkillResponse addSkill(SkillRequest request);
    SkillResponse updateSkill(Long id, SkillRequest request);
    void deleteSkill(Long id);
    SkillResponse getSkillById(Long id);
    List<SkillResponse> getAllSkills();
    List<SkillResponse> getSkillsByEmployee(Long employeeId);
    List<SkillResponse> getSkillsByCategory(SkillCategory category);
    List<SkillResponse> getVerifiedSkills();
    SkillResponse verifySkill(Long id);
}