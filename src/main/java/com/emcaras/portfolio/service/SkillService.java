package com.emcaras.portfolio.service;

import com.emcaras.portfolio.model.Skill;
import com.emcaras.portfolio.repository.ISkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillService implements ISkillService{

    private final ISkillRepository skillRepository;

    @Override
    @Transactional
    public Skill save(Skill skill) {
        return this.skillRepository.save(skill);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Skill> findAll() {
        return this.skillRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Skill> findById(Long id) {
        return this.skillRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Skill> findByPersonalInfoId(Long id) {
        return this.skillRepository.findByPersonalInfoId(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.skillRepository.delete(id);
    }
}
