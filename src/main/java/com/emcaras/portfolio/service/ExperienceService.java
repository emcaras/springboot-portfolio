package com.emcaras.portfolio.service;

import com.emcaras.portfolio.model.Experience;
import com.emcaras.portfolio.repository.IExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExperienceService implements IExperienceService {

    private final IExperienceRepository experienceRepository;

    @Override
    @Transactional
    public Experience save(Experience experience) {
        return this.experienceRepository.save(experience);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Experience> findAll() {
        return this.experienceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Experience> findById(Long id) {
        return this.experienceRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Experience> findByPersonalInfoId(Long id) {
        return this.experienceRepository.findByPersonalInfoId(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.experienceRepository.delete(id);
    }
}
