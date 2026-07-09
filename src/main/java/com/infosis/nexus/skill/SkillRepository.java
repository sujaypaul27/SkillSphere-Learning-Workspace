package com.infosis.nexus.skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    boolean existsByEmployeeIdAndSkillName(Long employeeId, String skillName);

    List<Skill> findByEmployeeId(Long employeeId);

    List<Skill> findByCategory(SkillCategory category);

    List<Skill> findByIsVerifiedTrue();
}