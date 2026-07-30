package com.infosis.nexus.SkillCatalog;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillCatalogService {

    private final SkillCatalogRepository repository;

    public SkillCatalogService(SkillCatalogRepository repository) {
        this.repository = repository;
    }

    public List<SkillCatalog> getAll() {
        return repository.findAll();
    }

    public SkillCatalog getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + id));
    }

    public SkillCatalog create(SkillCatalog skill) {
        return repository.save(skill);
    }

    public SkillCatalog update(Long id, SkillCatalog skill) {
        SkillCatalog existing = getById(id);
        existing.setName(skill.getName());
        existing.setCategory(skill.getCategory());
        return repository.save(existing);
    }

    public void delete(Long id) {
        SkillCatalog existing = getById(id);
        repository.delete(existing);
    }
}