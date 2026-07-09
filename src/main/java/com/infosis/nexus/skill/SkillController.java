package com.infosis.nexus.skill;

import com.infosis.nexus.skill.dto.SkillRequest;
import com.infosis.nexus.skill.dto.SkillResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public ResponseEntity<SkillResponse> addSkill(@Valid @RequestBody SkillRequest request) {
        SkillResponse response = skillService.addSkill(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillResponse> updateSkill(@PathVariable Long id,
                                                     @Valid @RequestBody SkillRequest request) {
        return ResponseEntity.ok(skillService.updateSkill(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillResponse> getSkillById(@PathVariable Long id) {
        return ResponseEntity.ok(skillService.getSkillById(id));
    }

    @GetMapping
    public ResponseEntity<List<SkillResponse>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<SkillResponse>> getSkillsByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(skillService.getSkillsByEmployee(employeeId));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<SkillResponse>> getSkillsByCategory(@PathVariable SkillCategory category) {
        return ResponseEntity.ok(skillService.getSkillsByCategory(category));
    }

    @GetMapping("/verified")
    public ResponseEntity<List<SkillResponse>> getVerifiedSkills() {
        return ResponseEntity.ok(skillService.getVerifiedSkills());
    }

    @PatchMapping("/{id}/verify")
    public ResponseEntity<SkillResponse> verifySkill(@PathVariable Long id) {
        return ResponseEntity.ok(skillService.verifySkill(id));
    }
}