package com.example.jobcrony.data.repositories;

import com.example.jobcrony.data.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
