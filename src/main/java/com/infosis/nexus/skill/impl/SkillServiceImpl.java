package com.infosis.nexus.skill.impl;

import com.infosis.nexus.skill.*;
import com.infosis.nexus.skill.dto.SkillRequest;
import com.infosis.nexus.skill.dto.SkillResponse;
import com.infosis.nexus.skill.exception.*;
import com.infosis.nexus.skill.mapper.SkillMapper;
import com.infosis.nexus.Employee.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final EmployeeRepository employeeRepository;
    private final SkillMapper skillMapper;

    // Constructor injection — preferred over @Autowired on fields.
    // Makes dependencies explicit and the class easily testable.
    public SkillServiceImpl(SkillRepository skillRepository,
                            EmployeeRepository employeeRepository,
                            SkillMapper skillMapper) {
        this.skillRepository = skillRepository;
        this.employeeRepository = employeeRepository;
        this.skillMapper = skillMapper;
    }

    @Override
    public SkillResponse addSkill(SkillRequest request) {
        // Business rule: employee must exist before adding a skill
        if (!employeeRepository.existsById(request.getEmployeeId())) {
            throw new IllegalArgumentException("Employee not found with id: " + request.getEmployeeId());
        }

        // Business rule: no duplicate skill for the same employee
        boolean alreadyExists = skillRepository.existsByEmployeeIdAndSkillName(
                request.getEmployeeId(), request.getSkillName());
        if (alreadyExists) {
            throw new DuplicateSkillException(request.getEmployeeId(), request.getSkillName());
        }

        Skill skill = skillMapper.toEntity(request);
        Skill saved = skillRepository.save(skill);
        return skillMapper.toResponse(saved);
    }

    @Override
    public SkillResponse updateSkill(Long id, SkillRequest request) {
        Skill existing = skillRepository.findById(id)
                .orElseThrow(() -> new SkillNotFoundException(id));

        existing.setSkillName(request.getSkillName());
        existing.setCategory(request.getCategory());
        existing.setProficiencyLevel(request.getProficiencyLevel());
        existing.setYearsOfExperience(request.getYearsOfExperience());
        // Note: isVerified is NOT updated here — verification has its own endpoint (verifySkill)
        // This keeps state transitions explicit and auditable.

        Skill updated = skillRepository.save(existing);
        return skillMapper.toResponse(updated);
    }

    @Override
    public void deleteSkill(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new SkillNotFoundException(id));

        // Business rule: verified skills cannot be deleted
        if (Boolean.TRUE.equals(skill.getIsVerified())) {
            throw new VerifiedSkillDeletionException(id);
        }

        skillRepository.delete(skill);
    }

    @Override
    public SkillResponse getSkillById(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new SkillNotFoundException(id));
        return skillMapper.toResponse(skill);
    }

    @Override
    public List<SkillResponse> getAllSkills() {
        return skillRepository.findAll()
                .stream()
                .map(skillMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<SkillResponse> getSkillsByEmployee(Long employeeId) {
        return skillRepository.findByEmployeeId(employeeId)
                .stream()
                .map(skillMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<SkillResponse> getSkillsByCategory(SkillCategory category) {
        return skillRepository.findByCategory(category)
                .stream()
                .map(skillMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<SkillResponse> getVerifiedSkills() {
        return skillRepository.findByIsVerifiedTrue()
                .stream()
                .map(skillMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SkillResponse verifySkill(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new SkillNotFoundException(id));
        skill.setIsVerified(true);
        Skill saved = skillRepository.save(skill);
        return skillMapper.toResponse(saved);
    }
}