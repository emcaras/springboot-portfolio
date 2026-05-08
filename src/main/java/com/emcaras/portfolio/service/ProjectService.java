package com.emcaras.portfolio.service;

import com.emcaras.portfolio.model.Project;
import com.emcaras.portfolio.repository.IProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService implements IProjectService{

    private final IProjectRepository projectRepository;

    @Override
    @Transactional
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Project> findByPersonalInfoId(Long id) {
        return projectRepository.findByPersonalInfoId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        projectRepository.delete(id);
    }
}
