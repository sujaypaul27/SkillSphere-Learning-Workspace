package com.infosis.nexus.skill;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Skill name is required")
    private String name;

    @Enumerated(EnumType.STRING)
    private SkillCategory category;

    public Skill() {
    }

    public Skill(Long id, String name, SkillCategory category) {
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

    public SkillCategory getCategory() {
        return category;
    }

    public void setCategory(SkillCategory category) {
        this.category = category;
    }
}