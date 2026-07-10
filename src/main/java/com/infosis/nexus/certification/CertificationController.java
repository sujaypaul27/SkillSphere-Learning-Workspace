package com.infosis.nexus.certification;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certifications")
public class CertificationController {

    private final CertificationService service;

    public CertificationController(CertificationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Certification> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Certification getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Certification create(@Valid @RequestBody Certification certification) {
        return service.create(certification);
    }

    @PutMapping("/{id}")
    public Certification update(@PathVariable Long id,
                                @Valid @RequestBody Certification certification) {
        return service.update(id, certification);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Certification deleted successfully";
    }
}