package com.infosis.nexus.SkillCatalog;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skillcatalog")
public class SkillCatalogController {
    private final SkillCatalogService service;

    public SkillCatalogController(SkillCatalogService service) {
        this.service = service;
    }

    @GetMapping
    public List<SkillCatalog> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public SkillCatalog getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public SkillCatalog create(@Valid @RequestBody SkillCatalog skill) {
        return service.create(skill);
    }

    @PutMapping("/{id}")
    public SkillCatalog update(@PathVariable Long id, @Valid @RequestBody SkillCatalog skill) {
        return service.update(id, skill);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Skill deleted successfully";
    }
}