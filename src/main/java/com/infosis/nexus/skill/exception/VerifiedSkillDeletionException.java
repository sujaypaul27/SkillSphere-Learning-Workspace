package com.infosis.nexus.skill.exception;

public class VerifiedSkillDeletionException extends RuntimeException {
    public VerifiedSkillDeletionException( Long id)
    {
        super("Cannot delete  the verified skill with ID : " + id);
    }
}
