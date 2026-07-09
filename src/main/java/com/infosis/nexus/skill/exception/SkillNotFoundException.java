package com.infosis.nexus.skill.exception;

public class SkillNotFoundException extends RuntimeException {
    public SkillNotFoundException( Long id)
    {
        super("Skill not Found with Id: " + id);
    }
}
