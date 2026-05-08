package com.emcaras.portfolio.repository;

import com.emcaras.portfolio.model.Project;

import java.util.List;
import java.util.Optional;

public interface IProjectRepository {
    Project save(Project project);
    List<Project> findAll();
    Optional<Project> findById(Long id);
    List<Project> findByPersonalInfoId(Long id);
    void delete(Long id);
}
