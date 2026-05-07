package com.emcaras.portfolio.service;

import com.emcaras.portfolio.model.PersonalInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IPersonalInfoService {

    PersonalInfo save(PersonalInfo personalInfo);
    List<PersonalInfo> findAll();
    Optional<PersonalInfo> findById(Long id);
    void delete(Long id);
}
