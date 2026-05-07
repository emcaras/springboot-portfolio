package com.emcaras.portfolio.repository;

import com.emcaras.portfolio.model.PersonalInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPersonalInfoRepository  {
    PersonalInfo save(PersonalInfo personalInfo);
    Optional<PersonalInfo> findById(Long id);
    List<PersonalInfo> findAll();
    void delete(Long id);
}
