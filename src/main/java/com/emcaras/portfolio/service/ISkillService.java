package com.emcaras.portfolio.service;

import com.emcaras.portfolio.model.Skill;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ISkillService {
    Skill save(Skill skill);
    List<Skill> findAll();
    Optional<Skill> findById(Long id);
    List<Skill> findByPersonalInfoId(Long id);
    void delete(Long id);
}
