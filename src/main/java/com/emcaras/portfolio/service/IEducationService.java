package com.emcaras.portfolio.service;

import com.emcaras.portfolio.model.Education;

import java.util.List;
import java.util.Optional;

public interface IEducationService {
    Education save(Education education);
    List<Education> findAll();
    List<Education> findAllByPersonalInfoId(Long id);
    Optional<Education> findById(Long id);
    void delete(Long id);
}
