package com.infosis.nexus.skill;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {
    private final SkillService service;

    public SkillController(SkillService service) {
        this.service = service;
    }

    @GetMapping
    public List<Skill> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Skill getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Skill create(@Valid @RequestBody Skill skill) {
        return service.create(skill);
    }

    @PutMapping("/{id}")
    public Skill update(@PathVariable Long id, @Valid @RequestBody Skill skill) {
        return service.update(id, skill);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Skill deleted successfully";
    }
}