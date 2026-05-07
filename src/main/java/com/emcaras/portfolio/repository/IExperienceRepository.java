package com.emcaras.portfolio.repository;

import com.emcaras.portfolio.model.Experience;

import java.util.List;
import java.util.Optional;

public interface IExperienceRepository {
    Experience save(Experience experience);
    List<Experience> findAll();
    Optional<Experience> findById(Long id);
    List<Experience> findByPersonalInfoId(Long id);
    void delete(Long id);
}
