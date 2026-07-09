package com.infosis.nexus.skill.exception;

public class DuplicateSkillException extends  RuntimeException {
    public DuplicateSkillException(Long employeeId ,String skillName)
    {
        super("Employee "+employeeId+" alredy has this skill " + skillName);
    }
}
