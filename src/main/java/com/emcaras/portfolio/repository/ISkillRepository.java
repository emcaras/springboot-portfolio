package com.emcaras.portfolio.repository;

import com.emcaras.portfolio.model.Skill;

import java.util.List;
import java.util.Optional;

public interface ISkillRepository {
    Skill save(Skill skill);
    List<Skill> findAll();
    Optional<Skill> findById(Long id);
    List<Skill> findByPersonalInfoId(Long id);
    void delete(Long id);
}
