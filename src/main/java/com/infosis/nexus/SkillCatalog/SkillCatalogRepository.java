package com.infosis.nexus.SkillCatalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillCatalogRepository extends JpaRepository<SkillCatalog, Long> {
}