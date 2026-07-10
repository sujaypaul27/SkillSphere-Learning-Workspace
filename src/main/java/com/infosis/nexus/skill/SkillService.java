package com.infosis.nexus.skill;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository repository;

    public SkillService(SkillRepository repository) {
        this.repository = repository;
    }

    public List<Skill> getAll() {
        return repository.findAll();
    }

    public Skill getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + id));
    }

    public Skill create(Skill skill) {
        return repository.save(skill);
    }

    public Skill update(Long id, Skill skill) {
        Skill existing = getById(id);
        existing.setName(skill.getName());
        existing.setCategory(skill.getCategory());
        return repository.save(existing);
    }

    public void delete(Long id) {
        Skill existing = getById(id);
        repository.delete(existing);
    }
}