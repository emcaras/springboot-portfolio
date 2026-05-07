package com.emcaras.portfolio.service;

import com.emcaras.portfolio.model.PersonalInfo;
import com.emcaras.portfolio.repository.IPersonalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalInfoService implements IPersonalInfoService{

    private final IPersonalInfoRepository personalInfoRepository;

    @Override
    @Transactional
    public PersonalInfo save(PersonalInfo personalInfo) {
        return personalInfoRepository.save(personalInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonalInfo> findAll() {
        return personalInfoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersonalInfo> findById(Long id) {
        return personalInfoRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        personalInfoRepository.delete(id);
    }
}
