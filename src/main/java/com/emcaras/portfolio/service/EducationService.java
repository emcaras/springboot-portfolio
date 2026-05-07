package com.emcaras.portfolio.service;

import com.emcaras.portfolio.model.Education;
import com.emcaras.portfolio.repository.IEducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducationService implements IEducationService{

    private final IEducationRepository educationRepository;

    @Override
    @Transactional
    public Education save(Education education) {
        return this.educationRepository.save(education);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Education> findAll() {
        return this.educationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Education> findAllByPersonalInfoId(Long id) {
        return this.educationRepository.findByPersonalInfoId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Education> findById(Long id) {
        return this.educationRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.educationRepository.delete(id);
    }
}
