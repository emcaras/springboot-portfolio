package com.emcaras.portfolio.service;

import com.emcaras.portfolio.model.Project;

import java.util.List;
import java.util.Optional;

public interface IProjectService {
    Project save(Project project);
    List<Project> findAll();
    List<Project> findByPersonalInfoId(Long id);
    Optional<Project> findById(Long id);
    void delete(Long id);
}
