package com.infosis.nexus.SkillCatalog;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "skills")
public class SkillCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Skill name is required")
    private String name;

    @Enumerated(EnumType.STRING)
    private SkillCatalogCategory category;

    public SkillCatalog() {
    }

    public SkillCatalog(Long id, String name, SkillCatalogCategory category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SkillCatalogCategory getCategory() {
        return category;
    }

    public void setCategory(SkillCatalogCategory category) {
        this.category = category;
    }
}