package com.emcaras.portfolio.repository;

import com.emcaras.portfolio.model.Education;

import java.util.List;
import java.util.Optional;

public interface IEducationRepository {
    Education save(Education education);
    List<Education> findAll();
    List<Education> findByPersonalInfoId(Long id);
    Optional<Education> findById(Long id);
    void delete(Long id);
}
