package com.emcaras.portfolio.dto;

import com.emcaras.portfolio.model.Education;
import org.springframework.stereotype.Component;

@Component
public class EducationMapper {
    public Education toEntity(EducationDto data){
        Education education = new Education();
        education.setId(data.getId());
        education.setDegree(data.getDegree());
        education.setInstitution(data.getInstitution());
        education.setStartDate(data.getStartDate());
        education.setEndDate(data.getEndDate());
        education.setDescription(data.getDescription());
        education.setPersonalInfoId(data.getPersonalInfoId());
        return education;
    }

    public EducationDto toDto(Education data){
        EducationDto education = new EducationDto();
        education.setId(data.getId());
        education.setDegree(data.getDegree());
        education.setInstitution(data.getInstitution());
        education.setStartDate(data.getStartDate());
        education.setEndDate(data.getEndDate());
        education.setDescription(data.getDescription());
        education.setPersonalInfoId(data.getPersonalInfoId());
        return education;
    }


}
